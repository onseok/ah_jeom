package com.ssac.ah_jeom.src.main.locker

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.locker.models.GetLockerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LockerService(val view: LockerFragmentView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetLocker(){

        val getLockerRetrofitInterface = ApplicationClass.sRetrofit.create(
            LockerRetrofitInterface::class.java)
        getLockerRetrofitInterface.getLocker(userId).enqueue(object :
            Callback<GetLockerResponse> {
            override fun onResponse(call: Call<GetLockerResponse>, response: Response<GetLockerResponse>) {
                view.onGetLockerSuccess(response.body() as GetLockerResponse)
            }

            override fun onFailure(call: Call<GetLockerResponse>, t: Throwable) {
                view.onGetLockerFailure(t.message ?: "통신 오류")
            }
        })
    }

}