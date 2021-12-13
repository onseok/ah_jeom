package com.ssac.ah_jeom.src.detail.artistDetail.artistReview

import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.GetArtistReviewResponse
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.PostReportReviewResponse
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.ReportReviewRequest
import com.ssac.ah_jeom.src.detail.artistDetail.models.PostSubscribeResponse
import com.ssac.ah_jeom.src.detail.artistDetail.models.SubscribeRequest
import retrofit2.Call
import retrofit2.http.*

interface ArtistReviewRetrofitInterface {

    @GET("/app/users/{userId}/artist/{artistId}/reviews")
    fun getArtistReview(@Path("userId") userId: Int, @Path("artistId") artistId: Int, @Query("cursor") cursor: String?): Call<GetArtistReviewResponse>

    // 리뷰 신고 사유 작성
    @POST("/app/users/{userId}/report/review/{reviewId}")
    fun postReportReview(@Path("userId") userId: Int, @Path("reviewId") reviewId: Int, @Body reportReviewRequest: ReportReviewRequest): Call<PostReportReviewResponse>

}