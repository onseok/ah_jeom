package com.ssac.ah_jeom.src.detail.storageDetail

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityStorageDetailBinding
import com.ssac.ah_jeom.src.detail.storageDetail.adapter.StorageDetailRecyclerAdapter
import com.ssac.ah_jeom.src.detail.storageDetail.models.GetStorageDetailResponse
import com.ssac.ah_jeom.src.detail.storageDetail.models.StorageDetailRecyclerData

class StorageDetailActivity : BaseActivity<ActivityStorageDetailBinding>(ActivityStorageDetailBinding::inflate), StorageDetailActivityView {

    val data: MutableList<StorageDetailRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityStorageDetailBackButton.setOnClickListener{
            onBackPressed()
        }

        val storageId = intent.getIntExtra("storageId", 0)

        Log.d("storageId", storageId.toString())

        StorageDetailService(this).tryGetStorageDetail(storageId)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    private fun setStorageDetailRecyclerView() {
        val data: MutableList<StorageDetailRecyclerData> = data
        var adapter = StorageDetailRecyclerAdapter(this)
        adapter.listData = data
        binding.activityStorageDetailGridRecyclerView.adapter = adapter
        binding.activityStorageDetailGridRecyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    override fun onGetStorageDetailSuccess(response: GetStorageDetailResponse) {
        if (response.isSuccess) {
            // top
            Glide.with(this).load(response.result.top[0].img).into(binding.activityStorageDetailMainImage)
            binding.activityStorageDetailMainTitleText.text = response.result.top[0].title
            binding.activityStorageDetailCaptionExplanationText.text = response.result.top[0].caption
            binding.activityStorageDetailProfileName.text = response.result.top[0].nickname

            response.result.imgs.forEach {
                data.add(StorageDetailRecyclerData(it.img))
            }
        }
        setStorageDetailRecyclerView()
    }

    override fun onGetStorageDetailFailure(message: String) {
        showCustomToast("오류 : $message")
    }


}