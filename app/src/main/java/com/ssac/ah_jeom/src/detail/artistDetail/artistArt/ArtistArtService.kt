package com.ssac.ah_jeom.src.detail.artistDetail.artistArt

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.detail.artistDetail.artistArt.models.GetArtistArtResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistArtService(val view: ArtistArtActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetArtistArt(artistId: Int){

        val getArtistArtRetrofitInterface = ApplicationClass.sRetrofit.create(
            ArtistArtRetrofitInterface::class.java)
        getArtistArtRetrofitInterface.getArtistArt(userId, artistId).enqueue(object :
            Callback<GetArtistArtResponse> {
            override fun onResponse(call: Call<GetArtistArtResponse>, response: Response<GetArtistArtResponse>) {
                view.onGetArtistArtSuccess(response.body() as GetArtistArtResponse)
            }

            override fun onFailure(call: Call<GetArtistArtResponse>, t: Throwable) {
                view.onGetArtistArtFailure(t.message ?: "통신 오류")
            }
        })
    }

}