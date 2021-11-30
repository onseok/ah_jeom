package com.ssac.ah_jeom.src.main.subscribe.subscribeArtist

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.models.GetSubscribeArtistResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubscribeArtistService(val view: SubscribeArtistActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetSubscribeArtist(cursor: String?){

        val getSubscribeArtistRetrofitInterface = ApplicationClass.sRetrofit.create(
            SubscribeArtistRetrofitInterface::class.java)
        getSubscribeArtistRetrofitInterface.getSubscribeArtist(userId, cursor).enqueue(object :
            Callback<GetSubscribeArtistResponse> {
            override fun onResponse(call: Call<GetSubscribeArtistResponse>, response: Response<GetSubscribeArtistResponse>) {
                view.onGetSubscribeArtistSuccess(response.body() as GetSubscribeArtistResponse)
            }

            override fun onFailure(call: Call<GetSubscribeArtistResponse>, t: Throwable) {
                view.onGetSubscribeArtistFailure(t.message ?: "통신 오류")
            }
        })
    }

}