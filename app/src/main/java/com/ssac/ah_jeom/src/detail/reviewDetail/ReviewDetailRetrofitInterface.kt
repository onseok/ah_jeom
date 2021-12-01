package com.ssac.ah_jeom.src.detail.reviewDetail

import com.ssac.ah_jeom.src.detail.reviewDetail.models.PostReviewResponse
import com.ssac.ah_jeom.src.detail.reviewDetail.models.ReviewRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewDetailRetrofitInterface {

    // 구독 후기 작성
    @POST("/app/users/{userId}/subscribe/reviews")
    fun postReview(@Path("userId") userId: Int, @Body postReviewBody: ReviewRequest): Call<PostReviewResponse>

}