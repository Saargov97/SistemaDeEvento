package com.example.easycheckin.ui.inscricao

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.easycheckin.databinding.ItemInscricaoBinding
import com.example.easycheckin.databinding.ItemPositionBinding
import com.example.routemap.database.DAO.InscricaoDAO
import com.example.routemap.database.model.Inscricao

class InscricoesAdapter(
    private val onClick: (InscricaoDAO.NamedInscricao) -> Unit
) : ListAdapter<InscricaoDAO.NamedInscricao, InscricoesAdapter.InscricaoViewHolder>(InscricaoItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InscricaoViewHolder = InscricaoViewHolder(
        ItemInscricaoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: InscricaoViewHolder, inscricao: Int): Unit =
        holder.bind(getItem(inscricao))

    inner class InscricaoViewHolder(private val binding: ItemInscricaoBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var inscricao: InscricaoDAO.NamedInscricao

        init {
            binding.root.setOnClickListener { onClick(inscricao) }
        }

        fun bind(item: InscricaoDAO.NamedInscricao) {
            inscricao = item

            binding.tvEmail.text = item.email
            binding.tvNomPessoa.text = item.nom_pessoa?: "Indefinido"
            if (item.ind_checkin == 0) {
                binding.inscrBtnCheckin.text = "Ausente"
            } else {
                binding.inscrBtnCheckin.text = "Presente"
            }
            binding.inscrBtnCheckin.setOnClickListener {
                if (binding.inscrBtnCheckin.text.equals("Presente")) {
                    binding.inscrBtnCheckin.text = "Ausente"
                } else {
                    binding.inscrBtnCheckin.text = "Presente"
                }
            }
        }
    }
}

private class InscricaoItemCallback : DiffUtil.ItemCallback<InscricaoDAO.NamedInscricao>() {
    override fun areItemsTheSame(oldItem: InscricaoDAO.NamedInscricao, newItem: InscricaoDAO.NamedInscricao): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: InscricaoDAO.NamedInscricao, newItem: InscricaoDAO.NamedInscricao): Boolean =
        oldItem == newItem
}