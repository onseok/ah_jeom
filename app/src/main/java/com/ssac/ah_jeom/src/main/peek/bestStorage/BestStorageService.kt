package com.ssac.ah_jeom.src.main.peek.bestStorage

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.peek.bestStorage.models.GetBestStorageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BestStorageService(val view: BestStorageActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetBestStorage(cursor: String?) {

        val getBestStorageRetrofitInterface = ApplicationClass.sRetrofit.create(
            BestStorageRetrofitInterface::class.java)
        getBestStorageRetrofitInterface.getBestStorage(userId, cursor).enqueue(object :
            Callback<GetBestStorageResponse> {
            override fun onResponse(call: Call<GetBestStorageResponse>, response: Response<GetBestStorageResponse>) {
                view.onGetBestStorageSuccess(response.body() as GetBestStorageResponse)
            }

            override fun onFailure(call: Call<GetBestStorageResponse>, t: Throwable) {
                view.onGetBestStorageFailure(t.message ?: "통신 오류")
            }
        })
    }

}