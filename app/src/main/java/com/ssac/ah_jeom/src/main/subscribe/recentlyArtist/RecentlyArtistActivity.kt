package com.ssac.ah_jeom.src.main.subscribe.recentlyArtist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityRecentlyArtistBinding
import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.adapter.RecentlyArtistRecyclerAdapter
import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.models.RecentlyArtistRecyclerData

class RecentlyArtistActivity : BaseActivity<ActivityRecentlyArtistBinding>(ActivityRecentlyArtistBinding::inflate) {

    private val data: MutableList<RecentlyArtistRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val data: MutableList<RecentlyArtistRecyclerData> = loadData()
        var adapter = RecentlyArtistRecyclerAdapter(this)
        adapter.listData = data
        binding.activityRecentlyArtistMainRecyclerView.adapter = adapter
        binding.activityRecentlyArtistMainRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()

        binding.activityRecentlyArtistMainBackButton.setOnClickListener {
            onBackPressed()
        }
    }


    // 추후 API 연동 예정
    private fun loadData(): MutableList<RecentlyArtistRecyclerData> {

        data.add(RecentlyArtistRecyclerData(R.drawable.best_artist_recycler_image_1_temp, R.drawable.ic_platinum_icon, "Livia Kim", "208M명의 구독자", "백금아식"))
        data.add(RecentlyArtistRecyclerData(R.drawable.best_artist_recycler_image_2_temp, R.drawable.ic_gold_icon, "PoNA668", "146M명의 구독자", "골드아식"))
        data.add(RecentlyArtistRecyclerData(R.drawable.best_artist_recycler_image_3_temp, R.drawable.ic_silver_icon, "피시앤췹스", "798.6K명의 구독자", "실버아식"))
        data.add(RecentlyArtistRecyclerData(R.drawable.best_artist_recycler_image_4_temp, R.drawable.ic_standard_icon , "Levan Kenia", "676.8K명의 구독자", "평타아식"))

        return data
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}