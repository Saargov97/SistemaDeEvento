package com.example.easycheckin.ui.evento

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.easycheckin.databinding.ItemPositionBinding
import com.example.routemap.database.model.Evento

class EventosAdapter : ListAdapter<Evento, EventosAdapter.EventoViewHolder>(EventoItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder = EventoViewHolder(
        ItemPositionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: EventoViewHolder, evento: Int): Unit =
            holder.bind(getItem(evento))

    class EventoViewHolder(private val binding: ItemPositionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Evento) {
            binding.tvNomEvento.text = item.nom_evento
            binding.tvValor.text = "Valor: " //${item.vlr_evento}"
            binding.tvData.text = "Data: ${item.dta_evento}"
            /*Glide.with(binding.root.context)
                .load(urlProduto(item.produtoId))
                .placeholder(R.drawable.placeholder)
                .into(binding.ivIcon)*/
        }
    }
}

private class EventoItemCallback : DiffUtil.ItemCallback<Evento>() {
    override fun areItemsTheSame(oldItem: Evento, newItem: Evento): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Evento, newItem: Evento): Boolean =
            oldItem == newItem
}