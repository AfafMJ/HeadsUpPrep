package com.afaf.headsupprep


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afaf.headsupprep.databinding.CelebRowBinding

class RecyclerViewAdapter(private val celebs: Celeb):
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: CelebRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            CelebRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = celebs[position]
        holder.binding.apply {
            tvCelebName.text = item.name
            tvTaboo1.text = item.taboo1
            tvTaboo2.text = item.taboo2
            tvTaboo3.text = item.taboo3
        }
    }

    override fun getItemCount() = celebs.size
}