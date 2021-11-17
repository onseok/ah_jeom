package com.ssac.ah_jeom.src.splash

import com.ssac.ah_jeom.src.splash.models.IsValidateResponse
import retrofit2.Call
import retrofit2.http.GET

interface SplashRetrofitInterface {
    @GET("/app/auto-login")
    fun getIsValidate(): Call<IsValidateResponse>
}