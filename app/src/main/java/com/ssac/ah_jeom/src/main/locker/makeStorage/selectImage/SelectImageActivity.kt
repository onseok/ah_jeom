package com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivitySelectImageBinding
import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.adapter.SelectImageRecyclerAdapter
import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.adapter.SelectImageRecyclerAdapter.Companion.selectedImagesList
import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.adapter.SelectImageRecyclerData
import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.models.GetSelectImageResponse
import com.ssac.ah_jeom.src.userInfo.interests.InterestsService
import com.ssac.ah_jeom.src.userInfo.interests.models.PostInterestsRequest
import com.ssac.ah_jeom.src.userInfo.interests.recycler.InterestsRecyclerAdapter


class SelectImageActivity : BaseActivity<ActivitySelectImageBinding>(ActivitySelectImageBinding::inflate), SelectImageActivityView {

    val data: MutableList<SelectImageRecyclerData> = mutableListOf()
    private var cursor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activitySelectImageBackButton.setOnClickListener {
            showCustomToast("완료 버튼을 눌러주세요.")
        }

        binding.activitySelectImageCompleteText.setOnClickListener {

            if(!SelectImageRecyclerAdapter.COMPLETE_BUTTON) {
                showCustomToast("최소 1개 이상 선택해주세요.")
                return@setOnClickListener
            }
            else {
                val returnIntent = Intent()
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }

        }

        SelectImageService(this).tryGetSelectImage(cursor)

    }

    override fun onBackPressed() {
//        super.onBackPressed()
//        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        showCustomToast("완료 버튼을 눌러주세요.")
    }

    private fun setSelectImageView(response: GetSelectImageResponse) {
        val data: MutableList<SelectImageRecyclerData> = data
        var adapter = SelectImageRecyclerAdapter(this, response)
        adapter.listData = data
        binding.activitySelectImageRecyclerView.adapter = adapter
        binding.activitySelectImageRecyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    override fun onGetSelectImageSuccess(response: GetSelectImageResponse) {
        if (response.code == 3014) {
            binding.activitySelectImageRecyclerView.visibility = View.GONE
        }
        else if (response.isSuccess) {
            binding.activitySelectImageRecyclerView.visibility = View.VISIBLE

            data.clear()
            response.result.myimg.forEach {
                data.add(SelectImageRecyclerData(it.img))
            }
            setSelectImageView(response)
        }
    }

    override fun onGetSelectImageFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onResume() {
        super.onResume()
        SelectImageService(this).tryGetSelectImage(cursor)
    }
}