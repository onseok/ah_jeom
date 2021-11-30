package com.ssac.ah_jeom.src.detail.artistDetail

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.detail.artistDetail.models.GetArtistDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistDetailService(val view: ArtistDetailActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetArtistDetail(artistId: Int){

        val getArtistDetailRetrofitInterface = ApplicationClass.sRetrofit.create(
            ArtistDetailRetrofitInterface::class.java)
        getArtistDetailRetrofitInterface.getLocker(userId, artistId).enqueue(object :
            Callback<GetArtistDetailResponse> {
            override fun onResponse(call: Call<GetArtistDetailResponse>, response: Response<GetArtistDetailResponse>) {
                view.onGetArtistDetailSuccess(response.body() as GetArtistDetailResponse)
            }

            override fun onFailure(call: Call<GetArtistDetailResponse>, t: Throwable) {
                view.onGetArtistDetailFailure(t.message ?: "통신 오류")
            }
        })
    }

}