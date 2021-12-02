package com.ssac.ah_jeom.src.detail.storageDetail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityStorageDetailBinding
import com.ssac.ah_jeom.src.detail.artistDetail.ArtistDetailService
import com.ssac.ah_jeom.src.detail.artistDetail.models.SubscribeRequest
import com.ssac.ah_jeom.src.detail.storageDetail.adapter.StorageDetailRecyclerAdapter
import com.ssac.ah_jeom.src.detail.storageDetail.models.*

class StorageDetailActivity : BaseActivity<ActivityStorageDetailBinding>(ActivityStorageDetailBinding::inflate), StorageDetailActivityView {

    val data: MutableList<StorageDetailRecyclerData> = mutableListOf()

    private var globalStorageId = 0

    private val snackBarOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.snack_bar_open
        )
    }
    private val snackBarClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.snack_bar_close
        )
    }

    private var isLiked = false
    private var isSaved = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val storageId = intent.getIntExtra("storageId", 0)

        globalStorageId = storageId

        binding.activityStorageDetailBackButton.setOnClickListener{
            onBackPressed()
        }

        binding.activityStorageDetailLikeButton.setOnClickListener {
            if (!isLiked) {
                StorageDetailService(this).tryPostStorageLike(storageId)
            }
        }

        binding.activityStorageDetailLikeButtonTrue.setOnClickListener {
            if (isLiked) {
                StorageDetailService(this).tryPatchStorageLike(storageId)
            }
        }

        binding.activityStorageDetailSaveButton.setOnClickListener {
            if (!isSaved) {
                StorageDetailService(this).tryPostStorageSave(storageId)
            }
        }

        binding.activityStorageDetailSaveButtonTrue.setOnClickListener {
            if (isSaved) {
                StorageDetailService(this).tryPatchStorageSave(storageId)
            }
        }

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

    private fun showSnackBarLike() {
        binding.activityStorageDetailLikeButton.visibility = View.GONE
        binding.activityStorageDetailLikeButtonTrue.visibility = View.VISIBLE
        binding.activityStorageDetailLikeSnackBar.visibility = View.VISIBLE
        // snack bar open -> close
        binding.activityStorageDetailLikeSnackBar.startAnimation(snackBarOpen)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.activityStorageDetailLikeSnackBar.visibility = View.GONE
            binding.activityStorageDetailLikeSnackBar.startAnimation(snackBarClose)
        }, 2500)

        isLiked = true
    }

    private fun showSnackBarLikeDelete() {
        binding.activityStorageDetailLikeButton.visibility = View.VISIBLE
        binding.activityStorageDetailLikeButtonTrue.visibility = View.GONE
        binding.activityStorageDetailLikeSnackBarDelete.visibility = View.VISIBLE
        // snack bar open -> close
        binding.activityStorageDetailLikeSnackBarDelete.startAnimation(snackBarOpen)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.activityStorageDetailLikeSnackBarDelete.visibility = View.GONE
            binding.activityStorageDetailLikeSnackBarDelete.startAnimation(snackBarClose)
        }, 2500)

        isLiked = false
    }

    private fun showSnackBarSave() {
        binding.activityStorageDetailSaveButton.visibility = View.GONE
        binding.activityStorageDetailSaveButtonTrue.visibility = View.VISIBLE
        binding.activityStorageDetailSaveSnackBar.visibility = View.VISIBLE
        // snack bar open -> close
        binding.activityStorageDetailSaveSnackBar.startAnimation(snackBarOpen)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.activityStorageDetailSaveSnackBar.visibility = View.GONE
            binding.activityStorageDetailSaveSnackBar.startAnimation(snackBarClose)
        }, 2500)

        isSaved = true
    }

    private fun showSnackBarSaveDelete() {
        binding.activityStorageDetailSaveButton.visibility = View.VISIBLE
        binding.activityStorageDetailSaveButtonTrue.visibility = View.GONE
        binding.activityStorageDetailSaveSnackBarDelete.visibility = View.VISIBLE
        // snack bar open -> close
        binding.activityStorageDetailSaveSnackBarDelete.startAnimation(snackBarOpen)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.activityStorageDetailSaveSnackBarDelete.visibility = View.GONE
            binding.activityStorageDetailSaveSnackBarDelete.startAnimation(snackBarClose)
        }, 2500)

        isSaved = false
    }

    override fun onGetStorageDetailSuccess(response: GetStorageDetailResponse) {
        if (response.isSuccess) {
            // top
            Glide.with(this).load(response.result.top[0].img).into(binding.activityStorageDetailMainImage)
            binding.activityStorageDetailMainTitleText.text = response.result.top[0].title
            binding.activityStorageDetailCaptionExplanationText.text = response.result.top[0].caption
            binding.activityStorageDetailProfileName.text = response.result.top[0].nickname
            binding.activityStorageDetailDownloadText.text = "${response.result.top[0].save}회 다운"
            binding.activityStorageDetailProfileLikeNumberText.text = "좋아요 ${response.result.top[0].heart}개"
            // TODO 보관함 상세 원형 프로필 이미지 연동

            if(response.result.top[0].likes == 1) {
                binding.activityStorageDetailLikeButton.visibility = View.GONE
                binding.activityStorageDetailLikeButtonTrue.visibility = View.VISIBLE
                isLiked = true
            }
            else if (response.result.top[0].likes == 0) {
                binding.activityStorageDetailLikeButton.visibility = View.VISIBLE
                binding.activityStorageDetailLikeButtonTrue.visibility = View.GONE
                isLiked = false
            }

            if(response.result.top[0].store == 1) {
                binding.activityStorageDetailSaveButton.visibility = View.GONE
                binding.activityStorageDetailSaveButtonTrue.visibility = View.VISIBLE
                isSaved = true
            }
            else if (response.result.top[0].store == 0) {
                binding.activityStorageDetailSaveButton.visibility = View.VISIBLE
                binding.activityStorageDetailSaveButtonTrue.visibility = View.GONE
                isSaved = false
            }

            data.clear()
            response.result.imgs.forEach {
                data.add(StorageDetailRecyclerData(it.img))
            }
        }
        setStorageDetailRecyclerView()
    }

    override fun onGetStorageDetailFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPostStorageLikeSuccess(response: PostStorageLikeResponse) {
        if(response.isSuccess || response.code == 1000) {
            StorageDetailService(this).tryGetStorageDetail(globalStorageId)
            showSnackBarLike()
        }
        else {
            when (response.code) {
                3010 -> {
                    showCustomToast(response.message)
                }
                3021 -> {
                    showCustomToast(response.message)
                }
                3022 -> {
                    showCustomToast(response.message)
                }
            }
        }
    }

    override fun onPostStorageLikeFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPatchStorageLikeSuccess(response: PatchStorageLikeResponse) {
        if(response.isSuccess || response.code == 1000) {
            StorageDetailService(this).tryGetStorageDetail(globalStorageId)
            showSnackBarLikeDelete()
        }
        else {
            when (response.code) {
                3010 -> {
                    showCustomToast(response.message)
                }
                3021 -> {
                    showCustomToast(response.message)
                }
                3023 -> {
                    showCustomToast(response.message)
                }
            }
        }
    }

    override fun onPatchStorageLikeFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPostStorageSaveSuccess(response: PostStorageSaveResponse) {
        if(response.isSuccess || response.code == 1000) {
            StorageDetailService(this).tryGetStorageDetail(globalStorageId)
            showSnackBarSave()
        }
        else {
            when (response.code) {
                3010 -> {
                    showCustomToast(response.message)
                }
                3021 -> {
                    showCustomToast(response.message)
                }
                3024 -> {
                    showCustomToast(response.message)
                }
            }
        }
    }

    override fun onPostStorageSaveFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPatchStorageSaveSuccess(response: PatchStorageSaveResponse) {
        if(response.isSuccess || response.code == 1000) {
            StorageDetailService(this).tryGetStorageDetail(globalStorageId)
            showSnackBarSaveDelete()
        }
        else {
            when (response.code) {
                3010 -> {
                    showCustomToast(response.message)
                }
                3021 -> {
                    showCustomToast(response.message)
                }
                3025 -> {
                    showCustomToast(response.message)
                }
            }
        }
    }

    override fun onPatchStorageSaveFailure(message: String) {
        showCustomToast("오류 : $message")
    }


}