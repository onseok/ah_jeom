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

        data.add(RecentlyArtistRecyclerData(R.drawable.recently_artist_recycler_image_1_temp, R.drawable.ic_poo_icon, "김창완", "1명의 구독자", "응아아식"))
        data.add(RecentlyArtistRecyclerData(R.drawable.recently_artist_recycler_image_2_temp, R.drawable.ic_copper_icon, "FOLRO", "4명의 구독자", "구리아식"))
        data.add(RecentlyArtistRecyclerData(R.drawable.recently_artist_recycler_image_3_temp, R.drawable.ic_poo_icon, "루이지애나", "0명의 구독자", "응아아식"))
        data.add(RecentlyArtistRecyclerData(R.drawable.recently_artist_recycler_image_4_temp, R.drawable.ic_copper_icon , "최지우", "9명의 구독자", "구리아식"))

        return data
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}