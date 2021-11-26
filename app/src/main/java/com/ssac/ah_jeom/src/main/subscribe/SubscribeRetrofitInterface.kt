package com.ssac.ah_jeom.src.main.subscribe

import com.ssac.ah_jeom.src.main.subscribe.models.GetSubscribeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SubscribeRetrofitInterface {

    @GET("/app/users/{userId}/subscribe")
    fun getSubscribe(@Path("userId") userId: Int): Call<GetSubscribeResponse>

}