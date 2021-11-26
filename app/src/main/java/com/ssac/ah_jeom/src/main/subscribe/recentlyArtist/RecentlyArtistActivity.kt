package com.ssac.ah_jeom.src.main.subscribe.recentlyArtist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityRecentlyArtistBinding
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.BestArtistService
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.models.BestArtistRecyclerData
import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.adapter.RecentlyArtistRecyclerAdapter
import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.models.GetRecentlyArtistResponse
import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.models.RecentlyArtistRecyclerData

class RecentlyArtistActivity : BaseActivity<ActivityRecentlyArtistBinding>(ActivityRecentlyArtistBinding::inflate), RecentlyArtistActivityView {

    private val data: MutableList<RecentlyArtistRecyclerData> = mutableListOf()

    private var cursor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RecentlyArtistService(this).tryGetRecentlyArtist(cursor)

        binding.activityRecentlyArtistMainBackButton.setOnClickListener {
            onBackPressed()
        }
    }


//    // 추후 API 연동 예정
//    private fun loadData(): MutableList<RecentlyArtistRecyclerData> {
//
//        data.add(RecentlyArtistRecyclerData(R.drawable.recently_artist_recycler_image_1_temp, R.drawable.ic_poo_icon, "김창완", "1명의 구독자", "응아아식"))
//        data.add(RecentlyArtistRecyclerData(R.drawable.recently_artist_recycler_image_2_temp, R.drawable.ic_copper_icon, "FOLRO", "4명의 구독자", "구리아식"))
//        data.add(RecentlyArtistRecyclerData(R.drawable.recently_artist_recycler_image_3_temp, R.drawable.ic_poo_icon, "루이지애나", "0명의 구독자", "응아아식"))
//        data.add(RecentlyArtistRecyclerData(R.drawable.recently_artist_recycler_image_4_temp, R.drawable.ic_copper_icon , "최지우", "9명의 구독자", "구리아식"))
//
//        return data
//    }

    private fun setRecentlyArtistRecyclerView() {
        val data: MutableList<RecentlyArtistRecyclerData> = data
        var adapter = RecentlyArtistRecyclerAdapter(this)
        adapter.listData = data
        binding.activityRecentlyArtistMainRecyclerView.adapter = adapter
        binding.activityRecentlyArtistMainRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    private fun detectIcon(grade: Int): Int {

        var imageId = 0

        when (grade) {
            1 -> {
                imageId = R.drawable.ic_platinum_icon
            }
            2 -> {
                imageId = R.drawable.ic_gold_icon
            }
            3 -> {
                imageId = R.drawable.ic_silver_icon
            }
            4 -> {
                imageId = R.drawable.ic_standard_icon
            }
            5 -> {
                imageId = R.drawable.ic_copper_icon
            }
            6 -> {
                imageId = R.drawable.ic_poo_icon
            }
            7 -> {
                imageId = R.drawable.ic_stone_icon
            }
        }

        return imageId
    }

    override fun onGetRecentlyArtistSuccess(response: GetRecentlyArtistResponse) {
        if (response.isSuccess) {
            response.result.sub.forEach {
                data.add(RecentlyArtistRecyclerData(it.profile, detectIcon(it.grade), it.nickname, "${it.subCount}명의 구독자", it.gName))
            }
        }
        setRecentlyArtistRecyclerView()
    }

    override fun onGetRecentlyArtistFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}