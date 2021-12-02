package com.ssac.ah_jeom.src.profile.settings

import com.ssac.ah_jeom.src.profile.settings.models.GetSettingsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SettingsRetrofitInterface {

    @GET("/app/users/{userId}/mypage/setting")
    fun getSettings(@Path("userId") userId: Int): Call<GetSettingsResponse>

}