package com.ssac.ah_jeom.src.profile.myArt

import com.ssac.ah_jeom.src.profile.myArt.models.GetMyArtResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyArtRetrofitInterface {

    @GET("/app/users/{userId}/artworks")
    fun getMyArt(@Path("userId") userId: Int, @Query("cursor") cursor: String?): Call<GetMyArtResponse>

}