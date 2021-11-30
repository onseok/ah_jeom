package com.ssac.ah_jeom.src.main.subscribe.bestArtist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityBestArtistBinding
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.adapter.BestArtistRecyclerAdapter
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.models.BestArtistRecyclerData
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.models.GetBestArtistResponse
import com.ssac.ah_jeom.src.profile.models.GetProfileResponse

class BestArtistActivity : BaseActivity<ActivityBestArtistBinding>(ActivityBestArtistBinding::inflate), BestArtistActivityView {

    private val data: MutableList<BestArtistRecyclerData> = mutableListOf()

    private var cursor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BestArtistService(this).tryGetBestArtist(cursor)

        binding.activityBestArtistMainBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    private fun setBestArtistRecyclerView(response: GetBestArtistResponse) {
        val data: MutableList<BestArtistRecyclerData> = data
        var adapter = BestArtistRecyclerAdapter(this, response)
        adapter.listData = data
        binding.activityBestArtistMainRecyclerView.adapter = adapter
        binding.activityBestArtistMainRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
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

    override fun onGetBestArtistSuccess(response: GetBestArtistResponse) {
        if (response.isSuccess) {
            response.result.best.forEach {
                data.add(BestArtistRecyclerData(it.profile, detectIcon(it.grade), it.nickname, "${it.subCount}명의 구독자", it.gName))
            }
        }
        setBestArtistRecyclerView(response)

    }

    override fun onGetBestArtistFailure(message: String) {
       showCustomToast("오류 : $message")
    }
}