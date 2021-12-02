package com.ssac.ah_jeom.src.profile.settings

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.profile.settings.models.GetSettingsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsService(val view: SettingsActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetSettings(){

        val getSettingsRetrofitInterface = ApplicationClass.sRetrofit.create(SettingsRetrofitInterface::class.java)
        getSettingsRetrofitInterface.getSettings(userId).enqueue(object :
            Callback<GetSettingsResponse> {
            override fun onResponse(call: Call<GetSettingsResponse>, response: Response<GetSettingsResponse>) {
                view.onGetSettingsSuccess(response.body() as GetSettingsResponse)
            }

            override fun onFailure(call: Call<GetSettingsResponse>, t: Throwable) {
                view.onGetSettingsFailure(t.message ?: "통신 오류")
            }
        })
    }

}