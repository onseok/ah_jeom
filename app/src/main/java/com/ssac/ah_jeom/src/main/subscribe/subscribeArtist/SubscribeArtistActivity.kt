package com.ssac.ah_jeom.src.main.subscribe.subscribeArtist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivitySubscribeArtistBinding
import com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.adapter.SubscribeArtistRecyclerAdapter
import com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.models.GetSubscribeArtistResponse
import com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.models.SubscribeArtistRecyclerData

class SubscribeArtistActivity : BaseActivity<ActivitySubscribeArtistBinding>(
    ActivitySubscribeArtistBinding::inflate), SubscribeArtistActivityView {

    private val data: MutableList<SubscribeArtistRecyclerData> = mutableListOf()

    var cursor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activitySubscribeBackButton.setOnClickListener {
            onBackPressed()
        }

        SubscribeArtistService(this).tryGetSubscribeArtist(cursor)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    private fun setSubscribeArtistRecyclerView(response: GetSubscribeArtistResponse) {
        val data: MutableList<SubscribeArtistRecyclerData> = data
        var adapter = SubscribeArtistRecyclerAdapter(this, response)
        adapter.listData = data
        binding.activitySubscribeRecyclerView.adapter = adapter
        binding.activitySubscribeRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    override fun onGetSubscribeArtistSuccess(response: GetSubscribeArtistResponse) {
        data.clear()
        if (response.code == 3012) {
            binding.activitySubscribeArtistNoItemText.visibility = View.VISIBLE
            binding.activitySubscribeRecyclerView.visibility = View.GONE
        }
        else if (response.isSuccess) {
            binding.activitySubscribeRecyclerView.visibility = View.VISIBLE
            binding.activitySubscribeArtistNoItemText.visibility = View.GONE
            response.result?.sub?.forEach {
                data.add(SubscribeArtistRecyclerData(it.profile, detectIcon(it.gName) , it.nickname, "${it.subCount}명의 구독자", it.gName))
            }
            setSubscribeArtistRecyclerView(response)
        }
    }

    override fun onGetSubscribeArtistFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    private fun detectIcon(gName: String): Int {

        var imageId = 0

        when (gName) {
            "백금아식" -> {
                imageId = R.drawable.ic_platinum_icon
            }
            "골드아식" -> {
                imageId = R.drawable.ic_gold_icon
            }
            "실버아식" -> {
                imageId = R.drawable.ic_silver_icon
            }
            "평타아식" -> {
                imageId = R.drawable.ic_standard_icon
            }
            "구리아식" -> {
                imageId = R.drawable.ic_copper_icon
            }
            "응아아식" -> {
                imageId = R.drawable.ic_poo_icon
            }
            "돌맹아식" -> {
                imageId = R.drawable.ic_stone_icon
            }
        }

        return imageId
    }

    override fun onResume() {
        super.onResume()
        SubscribeArtistService(this).tryGetSubscribeArtist(cursor)
    }

}