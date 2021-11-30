package com.ssac.ah_jeom.src.main.locker.myImage

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityMyImageBinding
import com.ssac.ah_jeom.src.main.locker.myImage.adapter.MyImageRecyclerAdapter
import com.ssac.ah_jeom.src.main.locker.myImage.models.GetMyImageResponse
import com.ssac.ah_jeom.src.main.locker.myImage.models.MyImageRecyclerData
import com.ssac.ah_jeom.src.search.adapter.RelatedImageRecyclerAdapter
import com.ssac.ah_jeom.src.search.models.RelatedImageRecyclerData

class MyImageActivity : BaseActivity<ActivityMyImageBinding>(ActivityMyImageBinding::inflate), MyImageActivityView {

    val data: MutableList<MyImageRecyclerData> = mutableListOf()

    private var cursor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityMyImageBackButton.setOnClickListener {
            onBackPressed()
        }

        MyImageService(this).tryGetMyImage(cursor)

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
    }

    override fun onGetMyImageSuccess(response: GetMyImageResponse) {
        if (response.isSuccess) {
            response.result.myimg.forEach {
                data.add(MyImageRecyclerData(it.img))
            }
            setMyImageView()
        }

    }

    override fun onGetMyImageFailure(message: String) {
        TODO("Not yet implemented")
    }

}