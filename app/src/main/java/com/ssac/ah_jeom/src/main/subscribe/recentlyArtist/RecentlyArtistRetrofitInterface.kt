package com.ssac.ah_jeom.src.main.subscribe.recentlyArtist

import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.models.GetRecentlyArtistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecentlyArtistRetrofitInterface {

    @GET("/app/users/{userId}/subscribe/recent")
    fun getRecentlyArtist(@Path("userId") userId: Int, @Query("cursor") cursor: String?): Call<GetRecentlyArtistResponse>

}