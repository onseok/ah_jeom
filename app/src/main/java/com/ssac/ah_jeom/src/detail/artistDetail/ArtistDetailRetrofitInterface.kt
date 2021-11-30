package com.ssac.ah_jeom.src.detail.artistDetail

import com.ssac.ah_jeom.src.detail.artistDetail.models.GetArtistDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistDetailRetrofitInterface {

    @GET("/app/users/{userId}/artist/{artistId}")
    fun getLocker(@Path("userId") userId: Int, @Path("artistId") artistId: Int): Call<GetArtistDetailResponse>

}