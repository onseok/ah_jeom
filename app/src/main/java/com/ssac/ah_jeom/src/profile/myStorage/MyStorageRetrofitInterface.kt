package com.ssac.ah_jeom.src.profile.myStorage

import com.ssac.ah_jeom.src.profile.myStorage.models.GetMyStorageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyStorageRetrofitInterface {

    @GET("/app/users/{userId}/mystorage")
    fun getMyStorage(@Path("userId") userId: Int): Call<GetMyStorageResponse>

}