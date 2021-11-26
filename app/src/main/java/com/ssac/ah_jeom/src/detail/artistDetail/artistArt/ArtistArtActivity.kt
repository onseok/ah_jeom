package com.ssac.ah_jeom.src.detail.artistDetail.artistArt

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityArtistArtBinding
import com.ssac.ah_jeom.databinding.ActivitySearchBinding
import com.ssac.ah_jeom.src.detail.artistDetail.artistArt.adapter.ArtistArtRecyclerAdapter
import com.ssac.ah_jeom.src.detail.artistDetail.artistArt.models.ArtistArtRecyclerData
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.adapter.ArtistReviewRecyclerAdapter
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.ArtistReviewRecyclerData
import com.ssac.ah_jeom.src.search.adapter.RecommendedKeywordRecyclerAdapter
import com.ssac.ah_jeom.src.search.adapter.RelatedImageRecyclerAdapter
import com.ssac.ah_jeom.src.search.models.RecommendedKeywordRecyclerData
import com.ssac.ah_jeom.src.search.models.RelatedImageRecyclerData

class ArtistArtActivity : BaseActivity<ActivityArtistArtBinding>(ActivityArtistArtBinding::inflate) {

    val data: MutableList<ArtistArtRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityArtistArtBackButton.setOnClickListener {
            onBackPressed()
        }

        // 관련 이미지 설정
        setArtistArtImageView()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }


    private fun setArtistArtImageView() {
        val data: MutableList<ArtistArtRecyclerData> = data
        var adapter = ArtistArtRecyclerAdapter(this)
        adapter.listData = data
        binding.activityArtistArtRecyclerView.adapter = adapter
        binding.activityArtistArtRecyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()

        setArtistArtImageData()
    }

    // 추후 api연동 예정
    private fun setArtistArtImageData() {
        data.add(ArtistArtRecyclerData(R.drawable.artist_art_recycler_item_1_temp))
        data.add(ArtistArtRecyclerData(R.drawable.artist_art_recycler_item_2_temp))
        data.add(ArtistArtRecyclerData(R.drawable.artist_art_recycler_item_3_temp))
        data.add(ArtistArtRecyclerData(R.drawable.artist_art_recycler_item_4_temp))
        data.add(ArtistArtRecyclerData(R.drawable.artist_art_recycler_item_5_temp))
        data.add(ArtistArtRecyclerData(R.drawable.artist_art_recycler_item_6_temp))
        data.add(ArtistArtRecyclerData(R.drawable.artist_art_recycler_item_7_temp))
        data.add(ArtistArtRecyclerData(R.drawable.artist_art_recycler_item_8_temp))
        data.add(ArtistArtRecyclerData(R.drawable.artist_art_recycler_item_9_temp))
        data.add(ArtistArtRecyclerData(R.drawable.artist_art_recycler_item_10_temp))
    }
}