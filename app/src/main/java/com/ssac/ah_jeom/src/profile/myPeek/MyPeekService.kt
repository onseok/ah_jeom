package com.ssac.ah_jeom.src.profile.myPeek

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.profile.myPeek.models.GetMyPeekResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPeekService(val view: MyPeekActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetMyPeek(cursor: String?){

        val getMyPeekRetrofitInterface = ApplicationClass.sRetrofit.create(
            MyPeekRetrofitInterface::class.java)
        getMyPeekRetrofitInterface.getMyPeek(userId, cursor).enqueue(object :
            Callback<GetMyPeekResponse> {
            override fun onResponse(call: Call<GetMyPeekResponse>, response: Response<GetMyPeekResponse>) {
                view.onGetMyPeekSuccess(response.body() as GetMyPeekResponse)
            }

            override fun onFailure(call: Call<GetMyPeekResponse>, t: Throwable) {
                view.onGetMyPeekFailure(t.message ?: "통신 오류")
            }
        })
    }

}