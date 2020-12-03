package com.example.easycheckin

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.easycheckin.api.EventoModel
import com.example.easycheckin.api.InscricaoModel
import com.example.easycheckin.api.RetrofitInterface
import com.example.easycheckin.api.UserModel
import com.example.easycheckin.database.EventoRepository
import com.example.easycheckin.database.InscricaoRepository
import com.example.easycheckin.database.UserRepository
import com.example.easycheckin.ui.evento.EventoViewModel
import com.example.easycheckin.ui.inscricao.InscricaoViewModel
import com.example.easycheckin.ui.user.UserViewModel
import com.example.routemap.database.AppDatabase
import com.example.routemap.database.PositionRepository
import com.example.routemap.database.model.Evento
import com.example.routemap.database.model.Inscricao
import com.example.routemap.database.model.Position
import com.example.routemap.database.model.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Sync.newInstance] factory method to
 * create an instance of this fragment.
 */
class Sync : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels ()

    private val eventosViewModel: EventoViewModel by activityViewModels ()

    private val inscricaoViewModel: InscricaoViewModel by activityViewModels ()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sync, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn: Button = view.findViewById(R.id.syncBtnSync)
        btn.setOnClickListener { view ->
            val rf = Retrofit.Builder()
                .baseUrl(RetrofitInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()

            var API = rf.create(RetrofitInterface::class.java)
            var get = API.users()
            var getEventos = API.events(1)
            var getInscricao = API.inscricoes(1)

            inscricaoViewModel.syncInscricoes()

            inscricaoViewModel.syncResult.observe(viewLifecycleOwner,
                Observer { syncResult ->
                    syncResult ?: return@Observer
                    syncResult.error?.let {
                        Snackbar.make(view, "Falha ao sincronizar checkin: " + it, Snackbar.LENGTH_LONG).setAction("Action", null).show()
                    }
                    syncResult.success?.let {
                        if (it == 1) {
                            Snackbar.make(
                                view,
                                "Checkin sincronizado com sucesso! " + it,
                                Snackbar.LENGTH_LONG
                            ).setAction("Action", null).show()
                        }
                    }
                })

            get?.enqueue(object: Callback<List<UserModel?>?> {
                override fun onResponse(call: Call<List<UserModel?>?>, response: Response<List<UserModel?>?>) {
                    var userlist : List<UserModel>? = response.body() as List<UserModel>
                    for (i in userlist!!.indices) {
                        userViewModel.insert(User(id = userlist[i].id, email = userlist[i].email!!, password = userlist[i].password!!,
                            password2 = userlist[i].password2!!, nom_pessoa = userlist[i].nom_pessoa,
                            num_cpf = userlist[i].num_cpf, ind_atualizado = 1))
                    }
                    Snackbar.make(view, "Sincronizado users!", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }

                override fun onFailure(call: Call<List<UserModel?>?>, t: Throwable) {
                    Snackbar.make(requireView(),  "" + t.message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
            })
            getEventos?.enqueue(object: Callback<List<EventoModel?>?> {
                override fun onResponse(call: Call<List<EventoModel?>?>, response: Response<List<EventoModel?>?>) {
                    var elist : List<EventoModel>? = response.body() as List<EventoModel>
                    for (i in elist!!.indices) {
                        eventosViewModel.insert(Evento(id = elist[i].id, nom_evento = elist[i].nom_evento, dta_evento = elist[i].dta_evento,
                            num_vaga = elist[i].num_vaga, vlr_evento = elist[i].vlr_evento, des_carga_horaria = elist[i].des_carga_horaria, ind_atualizado = 1))
                    }
                    Snackbar.make(view, "Sincronizado eventos!", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }

                override fun onFailure(call: Call<List<EventoModel?>?>, t: Throwable) {
                    Snackbar.make(requireView(),  "" + t.message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
            })
            getInscricao?.enqueue(object: Callback<List<InscricaoModel?>?> {
                override fun onResponse(call: Call<List<InscricaoModel?>?>, response: Response<List<InscricaoModel?>?>) {
                    var ilist : List<InscricaoModel>? = response.body() as List<InscricaoModel>
                    for (i in ilist!!.indices) {
                        inscricaoViewModel.insert(Inscricao(id = ilist[i].id, des_qrcode = ilist[i].des_qrcode, des_hash = ilist[i].des_hash,
                            ind_checkin = ilist[i].ind_checkin, userId = ilist[i].userId, eventoId = ilist[i].eventoId, ind_atualizado = 1)
                        )
                    }
                    Snackbar.make(view, "Sincronizado inscrições!", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }

                override fun onFailure(call: Call<List<InscricaoModel?>?>, t: Throwable) {
                    Snackbar.make(requireView(),  "" + t.message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
            })
            //Snackbar.make(view, "Sincronizado!!!", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Sync.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Sync().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}