package com.ssac.ah_jeom.src.profile.settings.changeImage

import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityChangeImageBinding
import com.ssac.ah_jeom.src.profile.settings.changeImage.models.GetImageResponse
import com.ssac.ah_jeom.src.profile.settings.changeImage.models.PatchImageRequest
import com.ssac.ah_jeom.src.profile.settings.changeImage.models.PatchImageResponse
import java.text.SimpleDateFormat
import java.util.*

class ChangeImageActivity :
    BaseActivity<ActivityChangeImageBinding>(ActivityChangeImageBinding::inflate),
    ChangeImageActivityView {

    val userKey = ApplicationClass.sSharedPreferences.getInt("userId", 0)
    var finalUri: Uri? = null
    var downloadUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityChangeImageBackButton.setOnClickListener {
            onBackPressed()
        }

        // 갤러리에서 이미지 가져오기
        binding.activityChangeImageMyGalleryLayout.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            launcher.launch(intent)
        }

        binding.activityChangeImageBottomSaveLayout.setOnClickListener {
            showLoadingDialog(this)
            uploadImageToFirebaseStorage(finalUri!!)
        }

        ChangeImageService(this).tryGetImage()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    override fun onGetImageSuccess(response: GetImageResponse) {
        if (response.isSuccess) {
            Glide.with(this).load(response.result[0].profile).circleCrop()
                .into(binding.activityChangeImageMainProfileImage)
        } else {
            showCustomToast(response.message)
            onBackPressed()
        }
    }

    override fun onGetImageFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPatchImageSuccess(response: PatchImageResponse) {
        if (response.isSuccess) {
            dismissLoadingDialog()
            showCustomToast("프로필 사진이 변경됐어요.")
            onBackPressed()
        }
    }

    override fun onPatchImageFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    private val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            var imageView: AppCompatImageView? = binding.activityChangeImageMainProfileImage

            if (it.resultCode == RESULT_OK && it.data != null) {
                var currentImageUri = it.data?.data
                if (currentImageUri != null) {
                    finalUri = currentImageUri
                }
                try {
                    currentImageUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            Glide.with(this).load(currentImageUri).circleCrop().into(imageView!!)
                        } else {
                            Glide.with(this).load(currentImageUri).circleCrop().into(imageView!!)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (it.resultCode == RESULT_CANCELED) {
                showCustomToast("사진 선택 취소")
            } else {
                Log.d("ActivityResult", "something wrong")
            }
        }

    //Firebase Storage에 이미지를 업로드 하는 함수
    private fun uploadImageToFirebaseStorage(uri: Uri?) {
        var storage: FirebaseStorage? = FirebaseStorage.getInstance()   //FirebaseStorage 인스턴스 생성
        //파일 이름 생성.
        var fileName = "${userKey}_PROFILE_IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss").format(Date())}.png"
        //파일 업로드, 다운로드, 삭제, 메타데이터 가져오기 또는 업데이트를 하기 위해 참조를 생성.
        //참조는 클라우드 파일을 가리키는 포인터라고 할 수 있음.
        var imagesRef = storage!!.reference.child("images/${userKey}/profile/")
            .child(fileName)    //기본 참조 위치/images/userId/${fileName}
        //이미지 파일 업로드
        imagesRef.putFile(uri!!).addOnSuccessListener {
            showCustomToast("업로드 성공!")
            // 이미지 파일 가져오기
            imagesRef.downloadUrl.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    downloadUri = task.result
                    // 이미지 다운로드가 완료되면 바로 이미지 업로드 레트로핏 서비스 호출
                    val patchImageRequest = PatchImageRequest(profile = downloadUri.toString())
                    ChangeImageService(this).tryPatchImage(patchImageRequest)
                } else {
                    showCustomToast("이미지 파일 다운로드 에러")
                }
            }
        }.addOnFailureListener {
            println(it)
            showCustomToast("업로드 실패")
        }
    }
}