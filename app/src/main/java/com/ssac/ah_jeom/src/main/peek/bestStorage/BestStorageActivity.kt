package com.ssac.ah_jeom.src.main.peek.bestStorage

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityBestStorageBinding
import com.ssac.ah_jeom.src.main.peek.bestStorage.adapter.BestStorageRecyclerAdapter
import com.ssac.ah_jeom.src.main.peek.bestStorage.models.BestStorageRecyclerData
import com.ssac.ah_jeom.src.main.peek.bestStorage.models.GetBestStorageResponse
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.adapter.BestArtistRecyclerAdapter
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.models.BestArtistRecyclerData

class BestStorageActivity : BaseActivity<ActivityBestStorageBinding>(ActivityBestStorageBinding::inflate), BestStorageActivityView {

    private val data: MutableList<BestStorageRecyclerData> = mutableListOf()

    private var cursor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityBestStorageMainBackButton.setOnClickListener {
            onBackPressed()
        }

        BestStorageService(this).tryGetBestStorage(cursor)

    }

    private fun setBestStorageRecyclerView(response: GetBestStorageResponse) {
        val data: MutableList<BestStorageRecyclerData> = data
        var adapter = BestStorageRecyclerAdapter(this, response)
        adapter.listData = data
        binding.activityBestStorageMainRecyclerView.adapter = adapter
        binding.activityBestStorageMainRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    override fun onGetBestStorageSuccess(response: GetBestStorageResponse) {
        if (response.isSuccess) {
            data.clear()
            response.result.best.forEach {
                data.add(BestStorageRecyclerData(it.profile, it.nickname, "${it.save}회 다운", it.img, it.title, "좋아요 ${it.heart}개",
                    it.likes == 1
                ))
            }
        }
        setBestStorageRecyclerView(response)
    }

    override fun onGetBestStorageFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onResume() {
        super.onResume()
        BestStorageService(this).tryGetBestStorage(cursor)
    }
}