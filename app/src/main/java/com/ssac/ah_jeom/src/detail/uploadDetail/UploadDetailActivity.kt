package com.ssac.ah_jeom.src.detail.uploadDetail

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
import kotlin.collections.ArrayList


class UploadDetailActivity :
    BaseActivity<ActivityUploadDetailBinding>(ActivityUploadDetailBinding::inflate) {

    var interestsId: Int = 0
    var keywordId: Int = 0
    val userKey = ApplicationClass.sSharedPreferences.getInt("userId", 0)
    var finalUri: Uri? = null
    var downloadUri: Uri? = null

    private var interestsItemsTemp = ArrayList<Int>()
    private var interestsItems = ArrayList<Int>()
    private var interestsChecked = ArrayList<Boolean>()


    private var keywordItemsTemp = ArrayList<Int>()
    private var keywordItems = ArrayList<Int>()
    private var keywordsChecked = ArrayList<Boolean>()

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
                for (item in checked) {
                    interestsChecked.add(item)
                }
                for (item in interestsItemsTemp) {
                    interestsItems.add(item)
                }
                interestsItemsTemp.clear()
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
                                keywordItems.add(which + 13)
                            } else if (keywordItems.contains(which + 13)) {
                                keywordItems.remove(which)
                            }
                        }
                    })
                .setPositiveButton("확인", listener)
                .setNegativeButton("취소", null)
                .show()
        }

        // 갤러리에서 이미지 가져오기
        binding.activityUploadDetailImage.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                launcher.launch(intent)
            }
        })

        binding.activityUploadDetailCompleteText.setOnClickListener {
            uploadImageToFirebaseStorage(finalUri!!)
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
                    // TODO 나머지 레트로핏 서비스
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