package com.ssac.ah_jeom.src.detail.artistDetail

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityArtistDetailBinding
import com.ssac.ah_jeom.src.detail.artistDetail.adapter.ArtistDetailArtRecyclerAdapter
import com.ssac.ah_jeom.src.detail.artistDetail.adapter.ArtistDetailReviewAdapter
import com.ssac.ah_jeom.src.detail.artistDetail.artistArt.ArtistArtActivity
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.ArtistReviewActivity
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.ReportReviewService
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.ReportReviewRequest
import com.ssac.ah_jeom.src.detail.artistDetail.models.*

class ArtistDetailActivity : BaseActivity<ActivityArtistDetailBinding>(ActivityArtistDetailBinding::inflate), ArtistDetailActivityView {

    val data: MutableList<ArtistDetailArtData> = mutableListOf()

    private var globalArtistId = 0

    private var reportNumber = 0

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

    private var currentPosition = 1
    private var myHandler = MyHandler()
    private val intervalTime = 3500.toLong() // 몇초 간격으로 페이지를 넘길것인지 (5000 = 5.0초)

    private var hasReview = false

    private var isSubscribed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artistId = intent.getIntExtra("artistId", 0)

        globalArtistId = artistId

        binding.activityArtistDetailBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.activityArtistDetailArtListButton.setOnClickListener {
            val artistId = intent.getIntExtra("artistId", 0)
            val intent = Intent(this, ArtistArtActivity::class.java)
            intent.putExtra("artistId", artistId)
            startActivity(intent)
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        binding.activityArtistDetailReviewButton.setOnClickListener {
            val intent = Intent(this, ArtistReviewActivity::class.java)
            intent.putExtra("artistId", artistId)
            startActivity(intent)
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        binding.activityArtistDetailSubscribeButton.setOnClickListener {
            if (!isSubscribed) {
                val subscribeRequest = SubscribeRequest(artistId = artistId)
                ArtistDetailService(this).tryPostSubscribe(subscribeRequest)
            }
        }

        binding.activityArtistDetailSubscribeButtonTrue.setOnClickListener {
            if (isSubscribed) {
                val subscribeRequest = SubscribeRequest(artistId = artistId)
                ArtistDetailService(this).tryPatchSubscribe(subscribeRequest)
            }
        }

        binding.activityArtistDetailReportText.setOnClickListener {
            reportDialog(artistId)
        }


        ArtistDetailService(this).tryGetArtistDetail(artistId)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    private fun autoScrollStart(intervalTime: Long) {
        myHandler.removeMessages(0) // 이거 안하면 핸들러가 1개, 2개, 3개 ... n개 만큼 계속 늘어남
        myHandler.sendEmptyMessageDelayed(0, intervalTime) // intervalTime 만큼 반복해서 핸들러를 실행하게 함
    }

    private fun autoScrollStop() {
        myHandler.removeMessages(0) // 핸들러를 중지시킴
    }

    private inner class MyHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            if (msg.what == 0) {
                binding.activityArtistDetailReviewViewpager.setCurrentItem(++currentPosition, true) // 다음 페이지로 이동
                autoScrollStart(intervalTime) // 스크롤을 계속 이어서 한다.
            }
        }
    }

    private fun showSnackBar() {
        binding.activityArtistDetailSubscribeButton.visibility = View.GONE
        binding.activityArtistDetailSubscribeButtonTrue.visibility = View.VISIBLE
        binding.activityArtistDetailSubscribeSnackBar.visibility = View.VISIBLE
        // snack bar open -> close
        binding.activityArtistDetailSubscribeSnackBar.startAnimation(snackBarOpen)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.activityArtistDetailSubscribeSnackBar.visibility = View.GONE
            binding.activityArtistDetailSubscribeSnackBar.startAnimation(snackBarClose)
        }, 3500)

        isSubscribed = true
    }

    private fun showSnackBarDelete() {
        binding.activityArtistDetailSubscribeButton.visibility = View.VISIBLE
        binding.activityArtistDetailSubscribeButtonTrue.visibility = View.GONE
        binding.activityArtistDetailSubscribeSnackBarCancel.visibility = View.VISIBLE
        // snack bar open -> close
        binding.activityArtistDetailSubscribeSnackBarCancel.startAnimation(snackBarOpen)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.activityArtistDetailSubscribeSnackBarCancel.visibility = View.GONE
            binding.activityArtistDetailSubscribeSnackBarCancel.startAnimation(snackBarClose)
        }, 3500)

        isSubscribed = false
    }

    private fun setReviewViewPager(reviewData: MutableList<ArtistDetailReview>) {
        binding.activityArtistDetailReviewViewpager.adapter = ArtistDetailReviewAdapter(reviewData)
        binding.activityArtistDetailReviewViewpager.orientation = ViewPager2.ORIENTATION_VERTICAL
        binding.activityArtistDetailReviewViewpager.setCurrentItem(currentPosition, false) // 현재 위치를 지정

        // 현재 몇 번째 배너인지 보여주는 숫자를 변경함
        val apply = binding!!.activityArtistDetailReviewViewpager.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                }
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        // 뷰페이저에서 손 떼었을때 / 뷰페이저 멈춰있을 때
                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)

                        // 뷰페이저 움직이는 중
                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                    }
                }
            })
        }
        binding.activityArtistDetailReviewViewpager.setCurrentItem(++currentPosition, true)
    }

    override fun onGetArtistDetailSuccess(response: GetArtistDetailResponse) {
        if (response.isSuccess) {

            //top
            binding.activityArtistDetailProfileName.text = response.result.top[0].nickname
            Glide.with(this).load(response.result.top[0].profile).circleCrop().into(binding.activityArtistDetailProfileImage)
            binding.activityArtistDetailProfileSloganText.text = response.result.top[0].summary
            binding.activityArtistDetailRateImage.setImageResource(detectIcon(response.result.top[0].grade))
            if(response.result.top[0].sub == 1) {
                binding.activityArtistDetailSubscribeButton.visibility = View.GONE
                binding.activityArtistDetailSubscribeButtonTrue.visibility = View.VISIBLE
                isSubscribed = true
            }
            else if (response.result.top[0].sub == 0) {
                binding.activityArtistDetailSubscribeButton.visibility = View.VISIBLE
                binding.activityArtistDetailSubscribeButtonTrue.visibility = View.GONE
                isSubscribed = false
            }


            //mid
            data.clear()
            response.result.mid.forEach {
                data.add(ArtistDetailArtData(it.img))
            }
            setArtistArtImageView()

            //bot
            val reviewData: MutableList<ArtistDetailReview> = mutableListOf()
            if (response.result.bot?.size == 0) {
                hasReview = false
                binding.activityArtistDetailReviewTextNone.visibility = View.VISIBLE
            }
            else {
                hasReview = true
                reviewData.clear()
                binding.activityArtistDetailReviewTextNone.visibility = View.GONE
                response.result.bot?.forEach {
                    reviewData.add(ArtistDetailReview(it.caption))
                }
                setReviewViewPager(reviewData)
            }

        }
    }

    private fun setArtistArtImageView() {
        val data: MutableList<ArtistDetailArtData> = data
        var adapter = ArtistDetailArtRecyclerAdapter(this)
        adapter.listData = data
        binding.activityArtistDetailArtListImageRecyclerView.adapter = adapter
        binding.activityArtistDetailArtListImageRecyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    private fun detectIcon(grade: Int): Int {

        var imageId = 0

        when (grade) {
            1 -> {
                imageId = R.drawable.ic_platinum_icon
            }
            2 -> {
                imageId = R.drawable.ic_gold_icon
            }
            3 -> {
                imageId = R.drawable.ic_silver_icon
            }
            4 -> {
                imageId = R.drawable.ic_standard_icon
            }
            5 -> {
                imageId = R.drawable.ic_copper_icon
            }
            6 -> {
                imageId = R.drawable.ic_poo_icon
            }
            7 -> {
                imageId = R.drawable.ic_stone_icon
            }
        }

        return imageId
    }

    private fun reportDialog(artistId: Int) {

        val items = arrayOf(
            "부적절한 닉네임",
            "공격/비속어 등의 부적절한 비난 지속",
            "게시물/리뷰 게시성 도배",
            "개인 정보 침해",
            "기타(사유 작성)"
        )
        var selectedItem: String? = null

        val builder = AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
            .setTitle("신고하기")
            .setSingleChoiceItems(items, -1) { dialog, which ->
                selectedItem = items[which]
                reportNumber = which + 1
            }
            .setPositiveButton("확인") { dialog, which ->
                if (reportNumber == 5) {
                    reportWriteDialog(artistId)
                }
                else {
                    val postReportArtistRequest = ReportArtistRequest(number = reportNumber, caption = null)
                    ArtistDetailService(this).tryPostReportArtist(artistId, postReportArtistRequest)
                }
            }
            .show()
    }

    private fun reportWriteDialog(artistId: Int) {
        val dialog = Dialog(this)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 백그라운드 컬러 투명 (이걸 해줘야 background가 설정해준 모양으로 변함)
        dialog.setContentView(R.layout.report_dialog_artist)
        dialog.setCancelable(true)    // 다이얼로그외에 다른 화면을 눌렀을 때 나가는 것 허용

        var reportArtistYesButton: Button? =
            dialog.findViewById(R.id.report_artist_yes_button) // 버튼을 dialog에 연결
        var reportArtistNoButton: Button? = dialog.findViewById(R.id.report_artist_no_button)


        reportArtistYesButton?.setOnClickListener {
            dialog.dismiss()

            val caption : EditText = dialog.findViewById(R.id.report_dialog_artist_edit_text)

            val postReportArtistRequest = ReportArtistRequest(number = reportNumber, caption = caption.text?.toString())

            ArtistDetailService(this).tryPostReportArtist(artistId, postReportArtistRequest)
        }

        reportArtistNoButton?.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onGetArtistDetailFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPostSubscribeSuccess(response: PostSubscribeResponse) {
        if(response.isSuccess || response.code == 1000) {
            showSnackBar()
        }
        else {
            when (response.code) {
                3009 -> {
                    showCustomToast(response.message)
                }
                2025 -> {
                    showCustomToast(response.message)
                }
                3010 -> {
                    showCustomToast(response.message)
                }
            }
        }
    }

    override fun onPostSubscribeFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPatchSubscribeSuccess(response: PatchSubscribeResponse) {
        if(response.isSuccess || response.code == 1000) {
            showSnackBarDelete()
        }
        else {
            when (response.code) {
                2029 -> {
                    showCustomToast(response.message)
                }
                2025 -> {
                    showCustomToast(response.message)
                }
                3010 -> {
                    showCustomToast(response.message)
                }
                3011 -> {
                    showCustomToast(response.message)
                }
            }
        }
    }

    override fun onPatchSubscribeFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPostReportArtistSuccess(response: PostReportArtistResponse) {
        if (response.isSuccess && response.code == 1000) {
            showCustomToast("신고가 접수되었습니다.")
        }
        else {
            showCustomToast("${response.message}")
            return
        }
    }

    override fun onPostReportArtistFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onResume() {
        super.onResume()
        ArtistDetailService(this).tryGetArtistDetail(globalArtistId)
    }
}