package com.ssac.ah_jeom.src.main.locker.myImage

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityMyImageBinding
import com.ssac.ah_jeom.src.main.locker.myImage.adapter.MyImageRecyclerAdapter
import com.ssac.ah_jeom.src.main.locker.myImage.models.MyImageRecyclerData
import com.ssac.ah_jeom.src.search.adapter.RelatedImageRecyclerAdapter
import com.ssac.ah_jeom.src.search.models.RelatedImageRecyclerData

class MyImageActivity : BaseActivity<ActivityMyImageBinding>(ActivityMyImageBinding::inflate) {

    val data: MutableList<MyImageRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityMyImageBackButton.setOnClickListener {
            onBackPressed()
        }

        setMyImageView()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    private fun setMyImageView() {
        val data: MutableList<MyImageRecyclerData> = data
        var adapter = MyImageRecyclerAdapter(this)
        adapter.listData = data
        binding.activityMyImageRecyclerView.adapter = adapter
        binding.activityMyImageRecyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()

        setMyImageData()
    }

    // 추후 api연동 예정
    private fun setMyImageData() {
        data.add(MyImageRecyclerData(R.drawable.search_image_1_temp))
        data.add(MyImageRecyclerData(R.drawable.search_image_2_temp))
        data.add(MyImageRecyclerData(R.drawable.search_image_3_temp))
        data.add(MyImageRecyclerData(R.drawable.search_image_4_temp))
        data.add(MyImageRecyclerData(R.drawable.search_image_5_temp))
        data.add(MyImageRecyclerData(R.drawable.search_image_6_temp))
        data.add(MyImageRecyclerData(R.drawable.search_image_7_temp))
        data.add(MyImageRecyclerData(R.drawable.search_image_8_temp))
    }

}