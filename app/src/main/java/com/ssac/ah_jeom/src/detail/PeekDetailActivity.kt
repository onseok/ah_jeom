package com.ssac.ah_jeom.src.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityPeekDetailBinding
import com.ssac.ah_jeom.src.detail.recycler.PeekDetailRecyclerAdapter
import com.ssac.ah_jeom.src.detail.recycler.PeekDetailRecyclerData

class PeekDetailActivity : BaseActivity<ActivityPeekDetailBinding>(ActivityPeekDetailBinding::inflate) {

    val data: MutableList<PeekDetailRecyclerData> = mutableListOf()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 메인 이미지 하단 둥글게
        with(binding.activityPeekDetailMainImage) {
            background = resources.getDrawable(R.drawable.image_bottom_round, null)
            clipToOutline
        }

        binding.activityPeekDetailBackButton.setOnClickListener{
            onBackPressed()
        }

        val data: MutableList<PeekDetailRecyclerData> = data
        var adapter = PeekDetailRecyclerAdapter(this)
        adapter.listData = data
        binding.activityPeekDetailGridRecyclerView.adapter = adapter
        binding.activityPeekDetailGridRecyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()

        setData()
    }

    // 추후에 api 연동하면 대체될 것. 지금은 임시 mock data
    private fun setData() {
        data.add(PeekDetailRecyclerData(R.drawable.peek_detail_image_1_temp))
        data.add(PeekDetailRecyclerData(R.drawable.peek_detail_image_2_temp))
        data.add(PeekDetailRecyclerData(R.drawable.peek_detail_image_3_temp))
        data.add(PeekDetailRecyclerData(R.drawable.peek_detail_image_4_temp))
        data.add(PeekDetailRecyclerData(R.drawable.peek_detail_image_5_temp))
        data.add(PeekDetailRecyclerData(R.drawable.peek_detail_image_6_temp))
        data.add(PeekDetailRecyclerData(R.drawable.peek_detail_image_7_temp))
        data.add(PeekDetailRecyclerData(R.drawable.peek_detail_image_8_temp))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }


}