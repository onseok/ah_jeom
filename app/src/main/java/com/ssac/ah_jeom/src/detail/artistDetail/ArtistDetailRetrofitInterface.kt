package com.ssac.ah_jeom.src.detail.artistDetail

import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.ReportReviewRequest
import com.ssac.ah_jeom.src.detail.artistDetail.models.*
import retrofit2.Call
import retrofit2.http.*

interface ArtistDetailRetrofitInterface {

    // 작가 상세 조회
    @GET("/app/users/{userId}/artist/{artistId}")
    fun getLocker(@Path("userId") userId: Int, @Path("artistId") artistId: Int): Call<GetArtistDetailResponse>

    // 작가 구독
    @POST("/app/users/{userId}/subscribe")
    fun postSubscribe(@Path("userId") userId: Int, @Body artistId: SubscribeRequest): Call<PostSubscribeResponse>

    // 작가 구독 취소
    @PATCH("/app/users/{userId}/subscribe")
    fun patchSubscribe(@Path("userId") userId: Int, @Body artistId: SubscribeRequest): Call<PatchSubscribeResponse>

    // 작가(계정) 신고
    @POST("/app/users/{userId}/report/artist/{artistId}")
    fun postReportArtist(@Path("userId") userId: Int, @Path("artistId") artistId: Int, @Body reportArtistRequest: ReportArtistRequest): Call<PostReportArtistResponse>

}