package com.ssac.ah_jeom.src.main.subscribe.subscribeArtist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivitySubscribeArtistBinding
import com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.adapter.SubscribeArtistRecyclerAdapter
import com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.models.SubscribeArtistRecyclerData

class SubscribeArtistActivity : BaseActivity<ActivitySubscribeArtistBinding>(
    ActivitySubscribeArtistBinding::inflate) {

    private val data: MutableList<SubscribeArtistRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data: MutableList<SubscribeArtistRecyclerData> = loadData()
        var adapter = SubscribeArtistRecyclerAdapter(this)
        adapter.listData = data
        binding.activitySubscribeRecyclerView.adapter = adapter
        binding.activitySubscribeRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()

        binding.activitySubscribeBackButton.setOnClickListener {
            onBackPressed()
        }

    }

    // 추후 API 연동 예정
    private fun loadData(): MutableList<SubscribeArtistRecyclerData> {

        data.add(SubscribeArtistRecyclerData(R.drawable.subscribe_artist_recycler_image_1_temp, R.drawable.ic_silver_icon, "잭슨 위너비", "3.4K명의 구독자", "실버아식"))
        data.add(SubscribeArtistRecyclerData(R.drawable.subscribe_artist_recycler_image_2_temp, R.drawable.ic_platinum_icon, "Livia KIM", "208M명의 구독자", "백금아식"))
        data.add(SubscribeArtistRecyclerData(R.drawable.subscribe_artist_recycler_image_3_temp, R.drawable.ic_platinum_icon, "BOOKOOK", "0.8K명의 구독자", "백금아식"))
        data.add(SubscribeArtistRecyclerData(R.drawable.subscribe_artist_recycler_image_4_temp, R.drawable.ic_gold_icon , "bule8864", "89명의 구독자", "골드아식"))

        return data
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

}