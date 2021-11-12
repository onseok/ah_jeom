package com.ssac.ah_jeom.src.main.home

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.home.models.GetHomeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val view: HomeFragmentView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetHome(){

        val getHomeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        getHomeRetrofitInterface.getHome(userId).enqueue(object :
            Callback<GetHomeResponse> {
            override fun onResponse(call: Call<GetHomeResponse>, response: Response<GetHomeResponse>) {
                view.onGetHomeSuccess(response.body() as GetHomeResponse)
            }

            override fun onFailure(call: Call<GetHomeResponse>, t: Throwable) {
                view.onGetHomeFailure(t.message ?: "통신 오류")
            }
        })
    }

}