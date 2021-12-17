package com.ssac.ah_jeom.src.detail.uploadDetail

import android.Manifest
import android.R.attr
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import com.google.firebase.storage.FirebaseStorage
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityUploadDetailBinding
import java.text.SimpleDateFormat
import java.util.*
import android.R.attr.checked

import android.content.DialogInterface.OnMultiChoiceClickListener
import android.content.pm.PackageManager
import com.ssac.ah_jeom.src.detail.uploadDetail.models.PostArtRequest
import com.ssac.ah_jeom.src.detail.uploadDetail.models.PostArtResponse
import com.ssac.ah_jeom.src.profile.settings.changeImage.ChangeImageActivity
import com.ssac.ah_jeom.src.profile.settings.changeImage.ChangeImageService
import com.ssac.ah_jeom.src.profile.settings.changeImage.models.PatchImageRequest
import kotlin.collections.ArrayList


class UploadDetailActivity :
    UploadDetailPermission(), UploadDetailActivityView {

    var interestsId: Int = 0
    var keywordId: Int = 0
    val userKey = ApplicationClass.sSharedPreferences.getInt("userId", 0)
    var finalUri: Uri? = null
    var downloadUri: Uri? = null

    private var interestsItemsTemp = mutableListOf<Int>()
    private var interestsItems = mutableListOf<Int>()
    private var interestsChecked = mutableListOf<Boolean>()
    private var interestsText = mutableListOf<String>()


    private var keywordItemsTemp = mutableListOf<Int>()
    private var keywordItems = mutableListOf<Int>()
    private var keywordsChecked = mutableListOf<Boolean>()
    private var keywordsText = mutableListOf<String>()


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

        binding.activityUploadDetailBackButton.setOnClickListener {
            onBackPressed()
            showCustomToast(downloadUri.toString())
        }

        interestsItemsTemp.clear()
        interestsItems.clear()
        interestsChecked.clear()

        keywordItemsTemp.clear()
        keywordItems.clear()
        keywordsChecked.clear()

        // 작품 분야 설정
        binding.activityUploadDetailInterestsLayout.setOnClickListener {
            val items = arrayOf(
                "그래픽", //1
                "일러스트", //2
                "현대미술", //3
                "추상", //4
                "만화", //5
                "조각", //6
                "사진", //7
                "자연", //8
                "인물", //9
                "건축", //10
                "3D", //11
                "조형", //12
            )
            var checked = booleanArrayOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )

            var listener = DialogInterface.OnClickListener { _, which ->
                interestsChecked.clear()
                interestsItems.clear()
                interestsText.clear()
                for (item in checked) {
                    interestsChecked.add(item)
                }
                for (item in interestsItemsTemp) {
                    interestsItems.add(item)
                }
                interestsItemsTemp.clear()

                for (i in 0 until items.size) {
                    if (interestsChecked[i]) {
                        interestsText.add(items[i])
                    }
                }

                var interestsTextArray = interestsText.toString()
                interestsTextArray = interestsTextArray.substring(1, interestsTextArray.length - 1)

                binding.activityUploadDetailInterestsText.text = interestsTextArray
            }

            AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
                .setMultiChoiceItems(
                    items,
                    checked,
                    object : DialogInterface.OnMultiChoiceClickListener {
                        override fun onClick(
                            dialog: DialogInterface?,
                            which: Int,
                            isChecked: Boolean
                        ) {
                            if (isChecked) {
                                interestsItemsTemp.add(which + 1)
                                checked[which] = true
                            } else if (interestsItemsTemp.contains(which + 1)) {
                                interestsItemsTemp.remove(which + 1)
                                checked[which] = false
                            }
                        }
                    })
                .setPositiveButton("확인", listener)
                .setNegativeButton("취소", null)
                .show()
        }

        binding.activityUploadDetailMainTitleText.setOnClickListener {
            showCustomToast(interestsChecked.toString())
            showCustomToast(interestsItems.toString())
            showCustomToast(keywordsChecked.toString())
            showCustomToast(keywordItems.toString())
        }

        // 작품 키워드 설정
        binding.activityUploadDetailKeywordLayout.setOnClickListener {
            val items = arrayOf(
                "심플한", //13
                "화려한", //14
                "강렬한", //15
                "편안한", //16
                "원색적인", //17
                "귀여운", //18
                "아름다운", //19
                "성스러운", //20
                "추상적인", //21
                "실제같은", //22
                "과장된", //23
                "재밌는", //24
                "무서운", //25
                "짐승같은", //26
                "신비로운" //27
            )
            var checked = booleanArrayOf(
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
            )
            var listener = DialogInterface.OnClickListener { _, which ->
                keywordsChecked.clear()
                keywordItems.clear()
                for (item in checked) {
                    keywordsChecked.add(item)
                }
                for (item in keywordItemsTemp) {
                    keywordItems.add(item)
                }
                keywordItemsTemp.clear()

                for (i in 0 until items.size) {
                    if (keywordsChecked[i]) {
                        keywordsText.add(items[i])
                    }
                }

                var keywordsTextArray = keywordsText.toString()
                keywordsTextArray = keywordsTextArray.substring(1, keywordsTextArray.length - 1)

                binding.activityUploadDetailKeywordText.text = keywordsTextArray
            }

            AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
                .setMultiChoiceItems(
                    items,
                    checked,
                    object : DialogInterface.OnMultiChoiceClickListener {
                        override fun onClick(
                            dialog: DialogInterface?,
                            which: Int,
                            isChecked: Boolean
                        ) {
                            if (isChecked) {
                                keywordItemsTemp.add(which + 13)
                            } else if (keywordItemsTemp.contains(which + 13)) {
                                keywordItemsTemp.remove(which)
                            }
                        }
                    })
                .setPositiveButton("확인", listener)
                .setNegativeButton("취소", null)
                .show()
        }

        // 모든 권한이 설정되었는지 확인하는 변수
        val isAllPermissionGranted = permissions.all {
            checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
        }

        // 갤러리에서 이미지 가져오기
        binding.activityUploadDetailImage.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
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
        })

        binding.activityUploadDetailCompleteText.setOnClickListener {
//            showCustomToast("현재 서버 이슈로 프로필 사진 변경이 불가합니다.\n빠른 시일 내에 해결하겠습니다.")
            showLoadingDialog(this)
            uploadImageToFirebaseStorage(finalUri!!)
            onBackPressed()
        }

    }

    private val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            var imageView: AppCompatImageView? = binding.activityUploadDetailImage

            if (it.resultCode == RESULT_OK && it.data != null) {
                var currentImageUri = it.data?.data
                if (currentImageUri != null) {
                    finalUri = currentImageUri
                }
                try {
                    currentImageUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            val bitmap = MediaStore.Images.Media.getBitmap(
                                this.contentResolver,
                                currentImageUri
                            )
                            imageView?.setImageBitmap(bitmap)
                        } else {
                            val source =
                                ImageDecoder.createSource(this.contentResolver, currentImageUri)
                            val bitmap = ImageDecoder.decodeBitmap(source)
                            imageView?.setImageBitmap(bitmap)
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

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    //Firebase Storage에 이미지를 업로드 하는 함수.
    private fun uploadImageToFirebaseStorage(uri: Uri?) {
        var storage: FirebaseStorage? = FirebaseStorage.getInstance()   //FirebaseStorage 인스턴스 생성
        //파일 이름 생성.
        var fileName = "${userKey}_IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss").format(Date())}.png"
        //파일 업로드, 다운로드, 삭제, 메타데이터 가져오기 또는 업데이트를 하기 위해 참조를 생성.
        //참조는 클라우드 파일을 가리키는 포인터라고 할 수 있음.
        var imagesRef = storage!!.reference.child("images/${userKey}/arts/")
            .child(fileName)    //기본 참조 위치/images/userId/${fileName}
        //이미지 파일 업로드
        imagesRef.putFile(uri!!).addOnSuccessListener {
            showCustomToast("업로드 성공!")
            // 이미지 파일 가져오기
            imagesRef.downloadUrl.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    downloadUri = task.result

                    val title = binding.activityUploadDetailTitleEditText.text.toString()
                    val caption = binding.activityUploadDetailExplanationEditText.text.toString()
                    val img = downloadUri.toString()
                    val price = binding.activityUploadDetailPriceEditText.text.toString()
                    val link = binding.activityUploadDetailLinkEditText.text.toString()
                    val fieldId = interestsItems
                    val kwId = keywordItems

                    val postArtRequest = PostArtRequest(title = title, caption = caption, img = img, price = price.toInt(), link = link, fieldId = fieldId, kwId = kwId)
                    UploadDetailService(this).tryPostArt(postArtRequest)
                } else {
                    showCustomToast("이미지 파일 다운로드 에러")
                }
            }
        }.addOnFailureListener {
            println(it)
            showCustomToast("업로드 실패")
        }
    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 98
    }

    override fun onPostArtSuccess(response: PostArtResponse) {
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

    override fun onPostArtFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}