package com.ssac.ah_jeom.src.main.peek.newStorage

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.peek.newStorage.models.GetNewStorageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewStorageService(val view: NewStorageActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetNewStorage(cursor: String?) {

        val getNewStorageRetrofitInterface = ApplicationClass.sRetrofit.create(
            NewStorageRetrofitInterface::class.java)
        getNewStorageRetrofitInterface.getNewStorage(userId, cursor).enqueue(object :
            Callback<GetNewStorageResponse> {
            override fun onResponse(call: Call<GetNewStorageResponse>, response: Response<GetNewStorageResponse>) {
                view.onGetNewStorageSuccess(response.body() as GetNewStorageResponse)
            }

            override fun onFailure(call: Call<GetNewStorageResponse>, t: Throwable) {
                view.onGetNewStorageFailure(t.message ?: "통신 오류")
            }
        })
    }

}