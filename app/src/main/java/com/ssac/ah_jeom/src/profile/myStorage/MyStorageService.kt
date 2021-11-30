package com.ssac.ah_jeom.src.profile.myStorage

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.profile.myStorage.models.GetMyStorageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyStorageService(val view: MyStorageActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetMyStorage(cursor: String?){

        val getMyStorageRetrofitInterface = ApplicationClass.sRetrofit.create(
            MyStorageRetrofitInterface::class.java)
        getMyStorageRetrofitInterface.getMyStorage(userId).enqueue(object :
            Callback<GetMyStorageResponse> {
            override fun onResponse(call: Call<GetMyStorageResponse>, response: Response<GetMyStorageResponse>) {
                view.onGetMyStorageSuccess(response.body() as GetMyStorageResponse)
            }

            override fun onFailure(call: Call<GetMyStorageResponse>, t: Throwable) {
                view.onGetMyStorageFailure(t.message ?: "통신 오류")
            }
        })
    }

}