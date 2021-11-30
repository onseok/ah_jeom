package com.ssac.ah_jeom.src.detail.artDetail

import com.ssac.ah_jeom.src.detail.artDetail.models.GetArtDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtDetailRetrofitInterface {

    @GET("/app/users/{userId}/artworks/{artId}")
    fun getArtDetail(@Path("userId") userId: Int, @Path("artId") artId: Int): Call<GetArtDetailResponse>

}