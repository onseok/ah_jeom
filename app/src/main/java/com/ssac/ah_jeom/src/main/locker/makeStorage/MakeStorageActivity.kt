package com.ssac.ah_jeom.src.main.locker.makeStorage

import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityMakeStorageBinding
import com.ssac.ah_jeom.src.main.locker.makeStorage.models.PostStorageRequest
import com.ssac.ah_jeom.src.main.locker.makeStorage.models.PostStorageResponse
import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.SelectImageActivity
import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.adapter.SelectImageRecyclerAdapter
import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.adapter.SelectImageRecyclerAdapter.Companion.selectedImagesList

class MakeStorageActivity : BaseActivity<ActivityMakeStorageBinding>(ActivityMakeStorageBinding::inflate), MakeStorageActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityMakeStorageImageLayout.setOnClickListener {
            val intent = Intent(this, SelectImageActivity::class.java) // 임시 나중에 바꿔야함
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
            launcher.launch(intent)
        }

        selectedImagesList.clear()
        SelectImageRecyclerAdapter.isSelected.clear()

        binding.activityMakeStorageCompleteText.setOnClickListener {
            if (selectedImagesList.size != 0) {
                val title = binding.activityMakeStorageTitleEditText.text.toString()
                val caption = binding.activityMakeStorageExplanationEditText.text.toString()
                selectedImagesList.sort()
                val postStorageRequest = PostStorageRequest(title = title, caption = caption, myimgId = selectedImagesList)
                showLoadingDialog(this)
                MakeStorageService(this).tryPostStorage(postStorageRequest)
            }
            else {
                showCustomToast("이미지를 최소 1장 이상 선택해주세요.")
                return@setOnClickListener
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    private val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if(it.resultCode == RESULT_OK) {
                try {
                    binding.activityMakeStorageImageHint.text = "이미지 ${selectedImagesList.size}개"
                }catch(e:Exception) {
                    e.printStackTrace()
                }
            } else if(it.resultCode == RESULT_CANCELED){
                showCustomToast("이미지 선택 취소")
            }else{
                Log.d("ActivityResult","something wrong")
            }
        }

    override fun onPostMakeStorageSuccess(response: PostStorageResponse) {
        if (response.isSuccess && response.code == 1000) {
            dismissLoadingDialog()
            showCustomToast("보관함이 생성되었어요.")
            onBackPressed()
        }
        else {
            dismissLoadingDialog()
            showCustomToast(response.message)
        }
    }

    override fun onPostMakeStorageFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}