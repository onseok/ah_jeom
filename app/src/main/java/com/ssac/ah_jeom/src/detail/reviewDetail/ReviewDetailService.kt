package com.ssac.ah_jeom.src.detail.reviewDetail

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.detail.reviewDetail.models.PostReviewResponse
import com.ssac.ah_jeom.src.detail.reviewDetail.models.ReviewRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewDetailService(val view: ReviewDetailActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryPostReview(postReviewBody: ReviewRequest){

        val postReviewRetrofitInterface = ApplicationClass.sRetrofit.create(
            ReviewDetailRetrofitInterface::class.java)
        postReviewRetrofitInterface.postReview(userId, postReviewBody).enqueue(object :
            Callback<PostReviewResponse> {
            override fun onResponse(call: Call<PostReviewResponse>, response: Response<PostReviewResponse>) {
                view.onPostReviewSuccess(response.body() as PostReviewResponse)
            }

            override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
                view.onPostReviewFailure(t.message ?: "통신 오류")
            }
        })
    }

}