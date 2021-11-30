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

    private fun setRecentlyArtistRecyclerView(response: GetRecentlyArtistResponse) {
        val data: MutableList<RecentlyArtistRecyclerData> = data
        var adapter = RecentlyArtistRecyclerAdapter(this, response)
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
        setRecentlyArtistRecyclerView(response)
    }

    override fun onGetRecentlyArtistFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}