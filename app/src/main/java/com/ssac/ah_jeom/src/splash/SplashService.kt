package com.ssac.ah_jeom.src.splash

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.splash.models.IsValidateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashService(val view: SplashActivityView) {

    fun tryGetIsValidate(){

        val isMemberRetrofitInterface = ApplicationClass.sRetrofit.create(SplashRetrofitInterface::class.java)
        isMemberRetrofitInterface.getIsValidate().enqueue(object : Callback<IsValidateResponse> {
            override fun onResponse(call: Call<IsValidateResponse>, response: Response<IsValidateResponse>) {
                view.onGetIsValidateSuccess(response.body() as IsValidateResponse)
            }

            override fun onFailure(call: Call<IsValidateResponse>, t: Throwable) {
                view.onGetIsValidateFailure(t.message ?: "통신 오류")
            }
        })
    }

}