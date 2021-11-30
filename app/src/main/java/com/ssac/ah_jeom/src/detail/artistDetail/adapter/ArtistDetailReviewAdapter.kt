package com.ssac.ah_jeom.src.detail.artistDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.src.detail.artistDetail.models.ArtistDetailReview

class ArtistDetailReviewAdapter(reviewList: MutableList<ArtistDetailReview>) : RecyclerView.Adapter<ArtistDetailReviewAdapter.PagerViewHolder>() {

    var item = reviewList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    } // 아이템의 갯수를 임의로 확 늘린다. 마치 무한으로 스크롤 되는 것처럼

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.review.text = item[position%(item.size)].review
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.activity_artist_detail_review_item, parent, false)) {
        val review: TextView = itemView.findViewById(R.id.activity_artist_detail_text)

    }

}