package com.example.verifeye

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

internal class MediaBiasInfoAdapter(private var mediaList: List<MediaBiasInfo>) :
    RecyclerView.Adapter<MediaBiasInfoAdapter.MediaViewHolder>(), Filterable {

    private var mediaListFiltered: List<MediaBiasInfo> = ArrayList(mediaList)

    internal inner class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var detailsTextView: TextView = itemView.findViewById(R.id.detailsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.media_bias_info_item, parent, false)
        return MediaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val media = mediaListFiltered[position]
        holder.nameTextView.text = media.name
        holder.detailsTextView.text = holder.itemView.context.getString(R.string.details_format, media.bias, media.factual, media.credibility)
    }

    override fun getItemCount(): Int = mediaListFiltered.size

    fun updateData(newMediaList: List<MediaBiasInfo>?) {
        if (newMediaList != null) {
            val oldSize = mediaListFiltered.size
            mediaList = ArrayList(newMediaList)
            mediaListFiltered = ArrayList(newMediaList)
            notifyItemRangeInserted(oldSize, mediaListFiltered.size - oldSize)
        } else {
            val oldSize = mediaListFiltered.size
            mediaList = arrayListOf()
            mediaListFiltered = arrayListOf()
            notifyItemRangeRemoved(0, oldSize)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                val filteredList = if (charString.isEmpty()) mediaList else {
                    mediaList.filter {
                        it.name.lowercase(Locale.getDefault()).contains(charString.lowercase(Locale.getDefault()))
                    }
                }
                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val newFiltered = (results?.values as? List<MediaBiasInfo>) ?: listOf()
                val oldSize = mediaListFiltered.size
                mediaListFiltered = newFiltered
                notifyItemRangeChanged(0, oldSize.coerceAtLeast(newFiltered.size))
            }
        }
    }

}
