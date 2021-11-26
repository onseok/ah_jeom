package com.ssac.ah_jeom.src.main.peek.bestStorage

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityBestStorageBinding
import com.ssac.ah_jeom.src.main.peek.bestStorage.adapter.BestStorageRecyclerAdapter
import com.ssac.ah_jeom.src.main.peek.bestStorage.models.BestStorageRecyclerData
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.adapter.BestArtistRecyclerAdapter
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.models.BestArtistRecyclerData

class BestStorageActivity : BaseActivity<ActivityBestStorageBinding>(ActivityBestStorageBinding::inflate) {

    private val data: MutableList<BestStorageRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data: MutableList<BestStorageRecyclerData> = loadData()
        var adapter = BestStorageRecyclerAdapter(this)
        adapter.listData = data
        binding.activityBestStorageMainRecyclerView.adapter = adapter
        binding.activityBestStorageMainRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()

        binding.activityBestStorageMainBackButton.setOnClickListener {
            onBackPressed()
        }

    }

    // 추후 API 연동 예정
    private fun loadData(): MutableList<BestStorageRecyclerData> {

        data.add(BestStorageRecyclerData(R.drawable.best_storage_recycler_profile_image_1_temp, "릴라안", "308K회 다운", R.drawable.best_storage_recycler_image_1_temp, "얼음 스크류바 BOOM", "좋아요 86.6K개"))
        data.add(BestStorageRecyclerData(R.drawable.best_storage_recycler_profile_image_2_temp, "김정희", "167.8K회 다운", R.drawable.best_storage_recycler_image_2_temp, "생명의 소리", "좋아요 56K개"))
        data.add(BestStorageRecyclerData(R.drawable.best_storage_recycler_profile_image_3_temp, "팔도강산", "154.6K회 다운", R.drawable.best_storage_recycler_image_3_temp, "꽃과 소녀", "좋아요 52.1K개"))

        return data
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}