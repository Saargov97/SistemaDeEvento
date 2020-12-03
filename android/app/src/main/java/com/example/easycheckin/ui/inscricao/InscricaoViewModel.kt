package com.example.easycheckin.ui.inscricao

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easycheckin.ui.inscricao.InscricaoFragmentArgs
import com.example.easycheckin.database.InscricaoRepository
import com.example.easycheckin.shared.navArgs
import com.example.routemap.database.model.Inscricao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InscricaoViewModel @ViewModelInject constructor (private val pRes: InscricaoRepository, @Assisted savedStateHandle: SavedStateHandle) : ViewModel() {
    val args: InscricaoFragmentArgs by savedStateHandle.navArgs()

    val inscricoes = pRes.allInscricoes
    fun namedInscricoes(eventoId: Int) = pRes.allNamedInscricoes(eventoId)

    fun insert(i: Inscricao) = viewModelScope.launch(Dispatchers.IO) {
        pRes.insert(i)
    }
}