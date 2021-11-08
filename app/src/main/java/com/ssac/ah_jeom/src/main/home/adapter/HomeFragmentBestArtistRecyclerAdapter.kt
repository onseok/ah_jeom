package com.ssac.ah_jeom.src.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.databinding.BestArtistRecyclerItemBinding
import com.ssac.ah_jeom.src.main.home.models.BestArtist

class HomeFragmentBestArtistRecyclerAdapter: RecyclerView.Adapter<Holder>() {

    var listData = mutableListOf<BestArtist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = BestArtistRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = listData[position]
        holder.setData(data)
    }

}

class Holder(val binding: BestArtistRecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun setData(data: BestArtist) {
        binding.bestArtistRecyclerProfileImage.setImageResource(data.profileImage)
        binding.bestArtistRecyclerName.text = data.name
        binding.bestArtistRecyclerRepresentativeImage.setImageResource(data.representativeImage)
    }
}