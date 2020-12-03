package com.example.easycheckin.ui.inscricao

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.easycheckin.api.InscricaoModel
import com.example.easycheckin.api.RetrofitInterface
import com.example.easycheckin.data.Result
import com.example.easycheckin.data.SyncResult
import com.example.easycheckin.ui.inscricao.InscricaoFragmentArgs
import com.example.easycheckin.database.InscricaoRepository
import com.example.easycheckin.shared.navArgs
import com.example.easycheckin.ui.login.LoggedInUserView
import com.example.easycheckin.ui.login.LoginResult
import com.example.routemap.database.model.Inscricao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InscricaoViewModel @ViewModelInject constructor (private val pRes: InscricaoRepository, @Assisted savedStateHandle: SavedStateHandle) : ViewModel() {
    val args: InscricaoFragmentArgs by savedStateHandle.navArgs()

    private val _syncResult = MutableLiveData<SyncResult>()
    val syncResult: LiveData<SyncResult> = _syncResult

    val inscricoes = pRes.allInscricoes
    fun syncInscricoes() = viewModelScope.launch {
        val rf = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API = rf.create(RetrofitInterface::class.java)
        var syncInscrs : List<Inscricao> =  pRes.allSyncInscricoes()
        var putCheckIn: Call<InscricaoModel?>? = null
        for (i in syncInscrs!!.indices) {
            val ins = InscricaoModel()
            ins.id = syncInscrs[i].id
            ins.ind_checkin = syncInscrs[i].ind_checkin
            putCheckIn = API.updateCheckin(syncInscrs[i].id, ins)

            putCheckIn?.enqueue(object : Callback<InscricaoModel?> {
                override fun onResponse(call: Call<InscricaoModel?>, response: Response<InscricaoModel?>) {
                    updateSync(syncInscrs[i].id)
                    _syncResult.value = SyncResult(success = 1)
                }

                override fun onFailure(call: Call<InscricaoModel?>, t: Throwable) {
                    _syncResult.value = SyncResult(error = t.message)
                }
            })
        }
        _syncResult.value = SyncResult(success = 1)
    }
    fun namedInscricoes(eventoId: Int) = pRes.allNamedInscricoes(eventoId)

    fun updateCheckin(id: Int) = viewModelScope.launch { pRes.updateCheckin(id) }
    fun updateSync(id: Int) = viewModelScope.launch { pRes.updateSync(id) }

    fun insert(i: Inscricao) = viewModelScope.launch(Dispatchers.IO) {
        pRes.insert(i)
    }
}