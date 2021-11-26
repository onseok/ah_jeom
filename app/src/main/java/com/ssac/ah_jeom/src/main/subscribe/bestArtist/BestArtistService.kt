package com.ssac.ah_jeom.src.main.subscribe.bestArtist

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.models.GetBestArtistResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BestArtistService(val view: BestArtistActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetBestArtist(cursor: String?){

        val getBestArtistRetrofitInterface = ApplicationClass.sRetrofit.create(
            BestArtistRetrofitInterface::class.java)
        getBestArtistRetrofitInterface.getBestArtist(userId, cursor).enqueue(object :
            Callback<GetBestArtistResponse> {
            override fun onResponse(call: Call<GetBestArtistResponse>, response: Response<GetBestArtistResponse>) {
                view.onGetBestArtistSuccess(response.body() as GetBestArtistResponse)
            }

            override fun onFailure(call: Call<GetBestArtistResponse>, t: Throwable) {
                view.onGetBestArtistFailure(t.message ?: "통신 오류")
            }
        })
    }

}