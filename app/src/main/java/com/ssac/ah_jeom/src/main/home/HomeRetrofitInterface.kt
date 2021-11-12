package com.ssac.ah_jeom.src.main.home

import com.ssac.ah_jeom.src.main.home.models.GetHomeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeRetrofitInterface {

    @GET("/app/users/{userId}/home")
    fun getHome(@Path("userId") userId: Int): Call<GetHomeResponse>

}