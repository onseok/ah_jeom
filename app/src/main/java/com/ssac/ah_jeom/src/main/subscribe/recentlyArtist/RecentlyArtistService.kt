package com.ssac.ah_jeom.src.main.subscribe.recentlyArtist

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.models.GetRecentlyArtistResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecentlyArtistService(val view: RecentlyArtistActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetRecentlyArtist(cursor: String?){

        val getRecentlyArtistRetrofitInterface = ApplicationClass.sRetrofit.create(
            RecentlyArtistRetrofitInterface::class.java)
        getRecentlyArtistRetrofitInterface.getRecentlyArtist(userId, cursor).enqueue(object :
            Callback<GetRecentlyArtistResponse> {
            override fun onResponse(call: Call<GetRecentlyArtistResponse>, response: Response<GetRecentlyArtistResponse>) {
                view.onGetRecentlyArtistSuccess(response.body() as GetRecentlyArtistResponse)
            }

            override fun onFailure(call: Call<GetRecentlyArtistResponse>, t: Throwable) {
                view.onGetRecentlyArtistFailure(t.message ?: "통신 오류")
            }
        })
    }

}