package com.example.easycheckin.ui.inscricao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.easycheckin.R
import com.example.easycheckin.databinding.FragmentEventosBinding
import com.example.easycheckin.databinding.FragmentInscricoesBinding
import com.example.easycheckin.ui.evento.EventoViewModel
import com.example.easycheckin.ui.evento.EventosAdapter
import com.example.easycheckin.ui.evento.EventosFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InscricoesFragment : Fragment(R.layout.fragment_inscricoes) {

    private val viewModel: InscricaoViewModel by viewModels()

    private val inscricoesAdapter = InscricoesAdapter {
      //  findNavController().navigate(EventosFragmentDirections.actionEventosFragmentToInscricaoFragment(it.id, it.nom_evento!!))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentInscricoesBinding.bind(view)
        binding.rvInscricoes.adapter = inscricoesAdapter

        viewModel.namedInscricoes(viewModel.args.eventoId).observe(viewLifecycleOwner, inscricoesAdapter::submitList)
    }

}