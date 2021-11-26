package com.ssac.ah_jeom.src.main.locker

import com.ssac.ah_jeom.src.main.locker.models.GetLockerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LockerRetrofitInterface {

    @GET("/app/users/{userId}/storage")
    fun getLocker(@Path("userId") userId: Int): Call<GetLockerResponse>

}