package com.ssac.ah_jeom.src.detail.artistDetail.artistReview

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.GetArtistReviewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistReviewService(val view: ArtistReviewActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetArtistReview(artistId: Int, cursor: String?){

        val getArtistReviewRetrofitInterface = ApplicationClass.sRetrofit.create(
            ArtistReviewRetrofitInterface::class.java)
        getArtistReviewRetrofitInterface.getArtistReview(userId, artistId, cursor).enqueue(object :
            Callback<GetArtistReviewResponse> {
            override fun onResponse(call: Call<GetArtistReviewResponse>, response: Response<GetArtistReviewResponse>) {
                view.onGetArtistReviewSuccess(response.body() as GetArtistReviewResponse)
            }

            override fun onFailure(call: Call<GetArtistReviewResponse>, t: Throwable) {
                view.onGetArtistReviewFailure(t.message ?: "통신 오류")
            }
        })
    }

}