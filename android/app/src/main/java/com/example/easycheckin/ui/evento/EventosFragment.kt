package com.example.easycheckin.ui.evento

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewGroupCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.easycheckin.R
import com.example.easycheckin.database.EventoRepository
import com.example.easycheckin.databinding.FragmentEventosBinding
import com.example.routemap.database.AppDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventosFragment : Fragment(R.layout.fragment_eventos) {

    private val viewModel: EventoViewModel by viewModels()

    private val eventosAdapter = EventosAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* if (view is ViewGroup)
            ViewGroupCompat.setTransitionGroup(view, true)*/

        val binding = FragmentEventosBinding.bind(view)
        binding.rvHEventos.adapter = eventosAdapter

        viewModel.eventos.observe(viewLifecycleOwner, eventosAdapter::submitList)

        /*require(view is RecyclerView)

        val eventoAdapter = EventosAdapter {}
        view.adapter = eventoAdapter

        eventoViewModel.eventos.observe(viewLifecycleOwner, eventoAdapter::submitList)*/
    }
}