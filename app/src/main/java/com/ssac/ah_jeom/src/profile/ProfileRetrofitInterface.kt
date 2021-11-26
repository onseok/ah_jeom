package com.ssac.ah_jeom.src.profile

import com.ssac.ah_jeom.src.profile.models.GetProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileRetrofitInterface {

    @GET("/app/users/{userId}/mypage")
    fun getProfile(@Path("userId") userId: Int): Call<GetProfileResponse>

}