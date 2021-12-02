package com.ssac.ah_jeom.src.main.locker.makeStorage

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.locker.makeStorage.models.PostStorageRequest
import com.ssac.ah_jeom.src.main.locker.makeStorage.models.PostStorageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MakeStorageService(val view: MakeStorageActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryPostStorage(postStorageRequest: PostStorageRequest){

        val postStorageRetrofitInterface = ApplicationClass.sRetrofit.create(
            MakeStorageRetrofitInterface::class.java)
        postStorageRetrofitInterface.postStorage(userId, postStorageRequest).enqueue(object :
            Callback<PostStorageResponse> {
            override fun onResponse(call: Call<PostStorageResponse>, response: Response<PostStorageResponse>) {
                view.onPostMakeStorageSuccess(response.body() as PostStorageResponse)
            }

            override fun onFailure(call: Call<PostStorageResponse>, t: Throwable) {
                view.onPostMakeStorageFailure(t.message ?: "통신 오류")
            }
        })
    }

}