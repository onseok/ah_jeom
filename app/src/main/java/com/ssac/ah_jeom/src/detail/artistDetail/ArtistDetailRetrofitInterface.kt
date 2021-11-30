package com.ssac.ah_jeom.src.detail.artistDetail

import com.ssac.ah_jeom.src.detail.artistDetail.models.GetArtistDetailResponse
import com.ssac.ah_jeom.src.detail.artistDetail.models.PatchSubscribeResponse
import com.ssac.ah_jeom.src.detail.artistDetail.models.PostSubscribeResponse
import com.ssac.ah_jeom.src.detail.artistDetail.models.SubscribeRequest
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

}