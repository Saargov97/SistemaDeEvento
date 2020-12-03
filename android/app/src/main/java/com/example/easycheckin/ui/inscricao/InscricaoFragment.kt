package com.example.easycheckin.ui.inscricao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.easycheckin.R
import com.example.easycheckin.databinding.FragmentEventosBinding
import com.example.easycheckin.shared.navigateSafe
import com.example.easycheckin.ui.evento.EventoViewModel
import com.example.easycheckin.ui.evento.EventosAdapter
import com.example.easycheckin.ui.evento.EventosFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InscricaoFragment : Fragment(R.layout.fragment_inscricao) {

    private val viewModel: InscricaoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvNomEvento = view.findViewById<TextView>(R.id.tvNomEvento)
        val btnInscricoes = view.findViewById<Button>(R.id.inscBtnLista)
        tvNomEvento.text = "Evento: " + viewModel.args.nomEvento

        btnInscricoes.setOnClickListener {
            findNavController().navigateSafe(InscricaoFragmentDirections.actionInscricaoFragmentToInscricoesFragment(viewModel.args.eventoId, viewModel.args.nomEvento))
        }
    }
}