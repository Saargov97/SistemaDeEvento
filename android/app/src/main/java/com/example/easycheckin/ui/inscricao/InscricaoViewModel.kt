package com.example.easycheckin.ui.inscricao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easycheckin.database.InscricaoRepository
import com.example.routemap.database.model.Inscricao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InscricaoViewModel(private val pRes: InscricaoRepository) : ViewModel() {

    val inscricoes = pRes.allInscricoes

    fun insert(i: Inscricao) = viewModelScope.launch(Dispatchers.IO) {
        pRes.insert(i)
    }
}