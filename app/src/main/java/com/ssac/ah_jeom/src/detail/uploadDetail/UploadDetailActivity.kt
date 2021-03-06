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
        // ?????? ?????? (????????? ?????? ????????? ?????? ????????? ??????, ????????? ????????? ?????? ??????)
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
            showCustomToast("?????? ???????????? ?????? ????????? ????????? ?????????.")
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

        // ?????? ?????? ??????
        binding.activityUploadDetailInterestsLayout.setOnClickListener {
            val items = arrayOf(
                "?????????", //1
                "????????????", //2
                "????????????", //3
                "??????", //4
                "??????", //5
                "??????", //6
                "??????", //7
                "??????", //8
                "??????", //9
                "??????", //10
                "3D", //11
                "??????", //12
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
                .setPositiveButton("??????", listener)
                .setNegativeButton("??????", null)
                .show()
        }

        binding.activityUploadDetailMainTitleText.setOnClickListener {
            showCustomToast(interestsChecked.toString())
            showCustomToast(interestsItems.toString())
            showCustomToast(keywordsChecked.toString())
            showCustomToast(keywordItems.toString())
        }

        // ?????? ????????? ??????
        binding.activityUploadDetailKeywordLayout.setOnClickListener {
            val items = arrayOf(
                "?????????", //13
                "?????????", //14
                "?????????", //15
                "?????????", //16
                "????????????", //17
                "?????????", //18
                "????????????", //19
                "????????????", //20
                "????????????", //21
                "????????????", //22
                "?????????", //23
                "?????????", //24
                "?????????", //25
                "????????????", //26
                "????????????" //27
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
                .setPositiveButton("??????", listener)
                .setNegativeButton("??????", null)
                .show()
        }

        // ?????? ????????? ?????????????????? ???????????? ??????
        val isAllPermissionGranted = permissions.all {
            checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
        }

        // ??????????????? ????????? ????????????
        binding.activityUploadDetailImage.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // ?????? ????????? ???????????? ??? ?????? ?????? ??????
                if (!isAllPermissionGranted) {
                    requirePermissions(
                        permissions, PERMISSION_REQUEST_CODE
                    )
                }
                // ?????? ????????? ??? ?????? ????????? ???
                else {
                    requirePermissions(
                        permissions, PERMISSION_REQUEST_CODE
                    )
                }
            }
        })

        binding.activityUploadDetailCompleteText.setOnClickListener {
//            showCustomToast("?????? ?????? ????????? ????????? ?????? ????????? ???????????????.\n?????? ?????? ?????? ?????????????????????.")
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
                showCustomToast("?????? ?????? ??????")
            } else {
                Log.d("ActivityResult", "something wrong")
            }
        }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    //Firebase Storage??? ???????????? ????????? ?????? ??????.
    private fun uploadImageToFirebaseStorage(uri: Uri?) {
        var storage: FirebaseStorage? = FirebaseStorage.getInstance()   //FirebaseStorage ???????????? ??????
        //?????? ?????? ??????.
        var fileName = "${userKey}_IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss").format(Date())}.png"
        //?????? ?????????, ????????????, ??????, ??????????????? ???????????? ?????? ??????????????? ?????? ?????? ????????? ??????.
        //????????? ???????????? ????????? ???????????? ??????????????? ??? ??? ??????.
        var imagesRef = storage!!.reference.child("images/${userKey}/arts/")
            .child(fileName)    //?????? ?????? ??????/images/userId/${fileName}
        //????????? ?????? ?????????
        imagesRef.putFile(uri!!).addOnSuccessListener {
            showCustomToast("????????? ??????!")
            // ????????? ?????? ????????????
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
                    showCustomToast("????????? ?????? ???????????? ??????")
                }
            }
        }.addOnFailureListener {
            println(it)
            showCustomToast("????????? ??????")
        }
    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 98
    }

    override fun onPostArtSuccess(response: PostArtResponse) {
        if (response.isSuccess) {
            dismissLoadingDialog()
            showCustomToast("????????? ????????? ???????????????.")
            onBackPressed()
        }
        else {
            dismissLoadingDialog()
            showCustomToast("????????? ????????? ?????????????????????.")
            onBackPressed()

        }
    }

    override fun onPostArtFailure(message: String) {
        showCustomToast("?????? : $message")
    }
}