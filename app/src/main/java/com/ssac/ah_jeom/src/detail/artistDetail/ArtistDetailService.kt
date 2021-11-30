package com.ssac.ah_jeom.src.detail.artistDetail

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.detail.artistDetail.models.GetArtistDetailResponse
import com.ssac.ah_jeom.src.detail.artistDetail.models.PatchSubscribeResponse
import com.ssac.ah_jeom.src.detail.artistDetail.models.PostSubscribeResponse
import com.ssac.ah_jeom.src.detail.artistDetail.models.SubscribeRequest
import com.ssac.ah_jeom.src.main.subscribe.models.GetSubscribeResponse
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

    fun tryPostSubscribe(artistId: SubscribeRequest){
        val tryPostRetrofitInterface = ApplicationClass.sRetrofit.create(ArtistDetailRetrofitInterface::class.java)
        tryPostRetrofitInterface.postSubscribe(userId, artistId).enqueue(object : Callback<PostSubscribeResponse> {
            override fun onResponse(call: Call<PostSubscribeResponse>, response: Response<PostSubscribeResponse>) {
                view.onPostSubscribeSuccess(response.body() as PostSubscribeResponse)
            }

            override fun onFailure(call: Call<PostSubscribeResponse>, t: Throwable) {
                view.onPostSubscribeFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPatchSubscribe(artistId: SubscribeRequest){
        val tryPatchRetrofitInterface = ApplicationClass.sRetrofit.create(ArtistDetailRetrofitInterface::class.java)
        tryPatchRetrofitInterface.patchSubscribe(userId, artistId).enqueue(object : Callback<PatchSubscribeResponse> {
            override fun onResponse(call: Call<PatchSubscribeResponse>, response: Response<PatchSubscribeResponse>) {
                view.onPatchSubscribeSuccess(response.body() as PatchSubscribeResponse)
            }

            override fun onFailure(call: Call<PatchSubscribeResponse>, t: Throwable) {
                view.onPatchSubscribeFailure(t.message ?: "통신 오류")
            }
        })
    }

}