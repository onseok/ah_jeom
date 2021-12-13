package com.ssac.ah_jeom.src.detail.artDetail

import com.ssac.ah_jeom.src.detail.artDetail.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ArtDetailRetrofitInterface {

    @GET("/app/users/{userId}/artworks/{artId}")
    fun getArtDetail(@Path("userId") userId: Int, @Path("artId") artId: Int): Call<GetArtDetailResponse>

    // 작품 다운
    @POST("/app/users/{userId}/myimg")
    fun postDownloadImage(@Path("userId") userId: Int, @Body artId: DownloadImageRequest): Call<PostDownloadImageResponse>

    // 작품(게시물) 신고
    @POST("/app/users/{userId}/report/artwork/{artId}")
    fun postReportArt(@Path("userId") userId: Int, @Path("artId") artId: Int, @Body postReportArtRequest: PostReportArtRequest): Call<PostReportArtResponse>

}