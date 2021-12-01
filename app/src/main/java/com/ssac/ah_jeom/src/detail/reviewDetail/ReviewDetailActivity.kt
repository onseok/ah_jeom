package com.ssac.ah_jeom.src.detail.reviewDetail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityReviewDetailBinding
import com.ssac.ah_jeom.src.detail.reviewDetail.models.PostReviewResponse
import com.ssac.ah_jeom.src.detail.reviewDetail.models.ReviewRequest

class ReviewDetailActivity : BaseActivity<ActivityReviewDetailBinding>(ActivityReviewDetailBinding::inflate), ReviewDetailActivityView {

    var isChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artistId = intent.getIntExtra("artistId", 0)

        binding.activityReviewDetailCompleteButton.setOnClickListener {
            val caption = binding.activityReviewDetailEditText.text.toString()
            val reviewRequest = ReviewRequest(artistId = artistId, caption = caption)
            ReviewDetailService(this).tryPostReview(reviewRequest)
        }

        binding.activityReviewDetailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                detectTextLength()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

    }

    private fun changeButtonColor() {
        if(isChanged) {
            binding.activityReviewDetailCompleteButton.backgroundTintList =
                ContextCompat.getColorStateList(this@ReviewDetailActivity, R.color.main_blue)
        }
        else {
            binding.activityReviewDetailCompleteButton.backgroundTintList =
                ContextCompat.getColorStateList(this@ReviewDetailActivity, R.color.util_gray)
        }
    }

    private fun detectTextLength() {
        if (binding.activityReviewDetailEditText.text.toString().isEmpty()) {
            isChanged = false
            changeButtonColor()
        }
        else {
            isChanged = true
            changeButtonColor()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    override fun onPostReviewSuccess(response: PostReviewResponse) {
        if (response.code == 2030) {
            showCustomToast("후기는 100자리 미만으로 작성해주세요.")
        }
        else if (response.isSuccess) {
            showCustomToast("후기가 등록됐어요.")
            onBackPressed()
        }
    }

    override fun onPostReviewFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}