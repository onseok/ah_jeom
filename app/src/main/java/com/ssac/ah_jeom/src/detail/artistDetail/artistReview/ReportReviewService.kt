package com.ssac.ah_jeom.src.detail.artistDetail.artistReview

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.PostReportReviewResponse
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.ReportReviewRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportReviewService(val view: ReportReviewActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryPostReportReview(reviewId: Int, reportReviewRequest: ReportReviewRequest){
        val tryPostReportReviewRetrofitInterface = ApplicationClass.sRetrofit.create(
            ArtistReviewRetrofitInterface::class.java)
        tryPostReportReviewRetrofitInterface.postReportReview(userId, reviewId, reportReviewRequest).enqueue(object :
            Callback<PostReportReviewResponse> {
            override fun onResponse(call: Call<PostReportReviewResponse>, response: Response<PostReportReviewResponse>) {
                view.onPostReportReviewSuccess(response.body() as PostReportReviewResponse)
            }

            override fun onFailure(call: Call<PostReportReviewResponse>, t: Throwable) {
                view.onPostReportReviewFailure(t.message ?: "통신 오류")
            }
        })
    }

}

