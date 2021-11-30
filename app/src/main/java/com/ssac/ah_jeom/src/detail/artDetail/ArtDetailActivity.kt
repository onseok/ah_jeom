package com.ssac.ah_jeom.src.detail.artDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityArtDetailBinding
import com.ssac.ah_jeom.src.detail.artDetail.adapter.ArtDetailReviewAdapter
import com.ssac.ah_jeom.src.detail.artDetail.models.ArtDetailReview
import com.ssac.ah_jeom.src.detail.artDetail.models.DownloadImageRequest
import com.ssac.ah_jeom.src.detail.artDetail.models.GetArtDetailResponse
import com.ssac.ah_jeom.src.detail.artDetail.models.PostDownloadImageResponse
import com.ssac.ah_jeom.src.detail.artistDetail.adapter.ArtistDetailReviewAdapter
import com.ssac.ah_jeom.src.detail.artistDetail.models.ArtistDetailReview
import com.ssac.ah_jeom.src.detail.artistDetail.models.SubscribeRequest
import java.text.DecimalFormat

class ArtDetailActivity : BaseActivity<ActivityArtDetailBinding>(ActivityArtDetailBinding::inflate), ArtDetailActivityView {

    private var currentPosition = 1
    private var myHandler = MyHandler()
    private val intervalTime = 3500.toLong() // 몇초 간격으로 페이지를 넘길것인지 (5000 = 5.0초)

    private var hasReview = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artId = intent.getIntExtra("artId", 0)

        binding.activityArtDetailBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.activityArtDetailDownloadButton.setOnClickListener {
            val downloadImageRequest = DownloadImageRequest(artId = artId)
            ArtDetailService(this).tryPostDownloadImage(downloadImageRequest)
        }

        ArtDetailService(this).tryGetArtDetail(artId)

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
                binding.activityArtDetailReviewViewpager.setCurrentItem(++currentPosition, true) // 다음 페이지로 이동
                autoScrollStart(intervalTime) // 스크롤을 계속 이어서 한다.
            }
        }
    }

    private fun setReviewViewPager(reviewData: MutableList<ArtDetailReview>) {
        binding.activityArtDetailReviewViewpager.adapter = ArtDetailReviewAdapter(reviewData)
        binding.activityArtDetailReviewViewpager.orientation = ViewPager2.ORIENTATION_VERTICAL
        binding.activityArtDetailReviewViewpager.setCurrentItem(currentPosition, false) // 현재 위치를 지정

        // 현재 몇 번째 배너인지 보여주는 숫자를 변경함
        val apply = binding!!.activityArtDetailReviewViewpager.apply {
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
        binding.activityArtDetailReviewViewpager.setCurrentItem(++currentPosition, true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    @SuppressLint("SetTextI18n")
    override fun onGetArtDetailSuccess(response: GetArtDetailResponse) {
        val dec = DecimalFormat("#,###") // 천의 자리에서 쉼표
        
        if (response.isSuccess) {
            Glide.with(this).load(response.result.art[0].img).into(binding.activityArtDetailMainImage)
            binding.activityArtDetailTitleText.text = response.result.art[0].title
            binding.activityArtDetailPriceText.text = "${dec.format(response.result.art[0].price)}원"

            val reviewData: MutableList<ArtDetailReview> = mutableListOf()
            if (response.result.review?.size == 0) {
                hasReview = false
                binding.activityArtDetailReviewTextNone.visibility = View.VISIBLE
            }
            else {
                hasReview = true
                binding.activityArtDetailReviewTextNone.visibility = View.GONE
                response.result.review?.forEach {
                    reviewData.add(ArtDetailReview(it.caption, it.nickname))
                }
                setReviewViewPager(reviewData)
            }
        }
        else {
            showCustomToast(response.message)
            onBackPressed()
        }
    }

    override fun onGetArtDetailFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPostDownloadImageSuccess(response: PostDownloadImageResponse) {
        if (response.code == 3013) {
            showCustomToast("이미지함에 존재하는 작품입니다.")
        }
        else if (response.isSuccess) {
            showCustomToast("다운로드가 완료되었습니다.")
        }
    }

    override fun onPostDownloadImageFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}