package com.ssac.ah_jeom.src.profile

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.profile.models.GetProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileService(val view: ProfileActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetProfile(){

        val getProfileRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
        getProfileRetrofitInterface.getProfile(userId).enqueue(object :
            Callback<GetProfileResponse> {
            override fun onResponse(call: Call<GetProfileResponse>, response: Response<GetProfileResponse>) {
                view.onGetProfileSuccess(response.body() as GetProfileResponse)
            }

            override fun onFailure(call: Call<GetProfileResponse>, t: Throwable) {
                view.onGetProfileFailure(t.message ?: "통신 오류")
            }
        })
    }

}