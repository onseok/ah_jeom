package com.ssac.ah_jeom.src.profile.myArt

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.profile.myArt.models.GetMyArtResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyArtService(val view: MyArtActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetMyArt(cursor: String?){

        val getMyArtRetrofitInterface = ApplicationClass.sRetrofit.create(
            MyArtRetrofitInterface::class.java)
        getMyArtRetrofitInterface.getMyArt(userId, cursor).enqueue(object :
            Callback<GetMyArtResponse> {
            override fun onResponse(call: Call<GetMyArtResponse>, response: Response<GetMyArtResponse>) {
                view.onGetMyArtSuccess(response.body() as GetMyArtResponse)
            }

            override fun onFailure(call: Call<GetMyArtResponse>, t: Throwable) {
                view.onGetMyArtFailure(t.message ?: "통신 오류")
            }
        })
    }

}