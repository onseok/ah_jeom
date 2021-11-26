package com.ssac.ah_jeom.src.main.subscribe.bestArtist

import com.ssac.ah_jeom.src.main.subscribe.bestArtist.models.GetBestArtistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BestArtistRetrofitInterface {

    @GET("/app/users/{userId}/subscribe/best")
    fun getBestArtist(@Path("userId") userId: Int, @Query("cursor") cursor: String?): Call<GetBestArtistResponse>

}