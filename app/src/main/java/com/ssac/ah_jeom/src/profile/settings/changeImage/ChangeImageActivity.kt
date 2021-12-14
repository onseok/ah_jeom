package com.ssac.ah_jeom.src.profile.settings.changeImage

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
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
import com.ssac.ah_jeom.util.PermissionActivity
import java.text.SimpleDateFormat
import java.util.*

class ChangeImageActivity :
    ChangeImagePermission(),
    ChangeImageActivityView {

    val userKey = ApplicationClass.sSharedPreferences.getInt("userId", 0)
    var finalUri: Uri? = null
    var downloadUri: Uri? = null

    private val permissions: Array<String> by lazy {
        // 권한 배열 (현재는 내부 저장소 읽기 권한만 있음, 추후에 추가될 수도 있음)
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    override fun permissionGranted(requestCode: Int) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            launcher.launch(intent)
        }
    }

    override fun permissionDenied(requestCode: Int) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            showCustomToast("앱의 저장공간 접근 권한을 허용해 주세요.")
            onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 모든 권한이 설정되었는지 확인하는 변수
        val isAllPermissionGranted = permissions.all {
            checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
        }

        binding.activityChangeImageBackButton.setOnClickListener {
            onBackPressed()
        }

        // 갤러리에서 이미지 가져오기
        binding.activityChangeImageMyGalleryLayout.setOnClickListener {
            // 권한 설정이 안되었을 때 권한 요청 팝업
            if (!isAllPermissionGranted) {
                requirePermissions(
                    permissions, PERMISSION_REQUEST_CODE
                )
            }
            // 권한 설정이 다 완료 되었을 때
            else {
                requirePermissions(
                    permissions, PERMISSION_REQUEST_CODE
                )
            }
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
        else {
            dismissLoadingDialog()
            showCustomToast("서버에 문제가 발생하였습니다.")
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
//            showCustomToast("업로드 성공!")
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
            showCustomToast("서버에 문제가 발생하여 업로드에 실패하였습니다.")
        }
    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 99
    }
}