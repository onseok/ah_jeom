package com.ssac.ah_jeom.src.main.peek.newStorage

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityNewStorageBinding
import com.ssac.ah_jeom.src.main.peek.newStorage.adapter.NewStorageRecyclerAdapter
import com.ssac.ah_jeom.src.main.peek.newStorage.models.NewStorageRecyclerData

class NewStorageActivity : BaseActivity<ActivityNewStorageBinding>(ActivityNewStorageBinding::inflate) {

    private val data: MutableList<NewStorageRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data: MutableList<NewStorageRecyclerData> = loadData()
        var adapter = NewStorageRecyclerAdapter(this)
        adapter.listData = data
        binding.activityNewStorageMainRecyclerView.adapter = adapter
        binding.activityNewStorageMainRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()

        binding.activityNewStorageMainBackButton.setOnClickListener {
            onBackPressed()
        }

    }

    // 추후 API 연동 예정
    private fun loadData(): MutableList<NewStorageRecyclerData> {

        data.add(NewStorageRecyclerData(R.drawable.new_storage_recycler_profile_image_1_temp, "송이", "11회 다운", R.drawable.new_storage_recycler_image_1_temp, "색동 저고리가 활짝 피었네, 다 같...", "좋아요 16개"))
        data.add(NewStorageRecyclerData(R.drawable.new_storage_recycler_profile_image_2_temp, "nam860", "8회 다운", R.drawable.new_storage_recycler_image_2_temp, "아보카도 진주", "좋아요 3개"))
        data.add(NewStorageRecyclerData(R.drawable.new_storage_recycler_profile_image_3_temp, "남청아", "23회 다운", R.drawable.new_storage_recycler_image_3_temp, "My Ring", "좋아요 17개"))

        return data
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }
}