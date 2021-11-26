package com.ssac.ah_jeom.src.main.subscribe

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.subscribe.models.GetSubscribeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubscribeService(val view: SubscribeFragmentView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetSubscribe(){

        val getSubscribeRetrofitInterface = ApplicationClass.sRetrofit.create(SubscribeRetrofitInterface::class.java)
        getSubscribeRetrofitInterface.getSubscribe(userId).enqueue(object :
            Callback<GetSubscribeResponse> {
            override fun onResponse(call: Call<GetSubscribeResponse>, response: Response<GetSubscribeResponse>) {
                view.onGetSubscribeSuccess(response.body() as GetSubscribeResponse)
            }

            override fun onFailure(call: Call<GetSubscribeResponse>, t: Throwable) {
                view.onGetSubscribeFailure(t.message ?: "통신 오류")
            }
        })
    }

}