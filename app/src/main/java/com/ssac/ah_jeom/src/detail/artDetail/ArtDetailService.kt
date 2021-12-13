package com.ssac.ah_jeom.src.detail.artDetail

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.detail.artDetail.models.*
import com.ssac.ah_jeom.src.detail.artistDetail.models.ReportArtistRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtDetailService(val view: ArtDetailActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetArtDetail(artId: Int){

        val getArtDetailRetrofitInterface = ApplicationClass.sRetrofit.create(
            ArtDetailRetrofitInterface::class.java)
        getArtDetailRetrofitInterface.getArtDetail(userId, artId).enqueue(object :
            Callback<GetArtDetailResponse> {
            override fun onResponse(call: Call<GetArtDetailResponse>, response: Response<GetArtDetailResponse>) {
                view.onGetArtDetailSuccess(response.body() as GetArtDetailResponse)
            }

            override fun onFailure(call: Call<GetArtDetailResponse>, t: Throwable) {
                view.onGetArtDetailFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostDownloadImage(artId: DownloadImageRequest){

        val postDownloadImageRetrofitInterface = ApplicationClass.sRetrofit.create(
            ArtDetailRetrofitInterface::class.java)
        postDownloadImageRetrofitInterface.postDownloadImage(userId, artId).enqueue(object :
            Callback<PostDownloadImageResponse> {
            override fun onResponse(call: Call<PostDownloadImageResponse>, response: Response<PostDownloadImageResponse>) {
                view.onPostDownloadImageSuccess(response.body() as PostDownloadImageResponse)
            }

            override fun onFailure(call: Call<PostDownloadImageResponse>, t: Throwable) {
                view.onPostDownloadImageFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostReportArt(artId: Int, postReportArtRequest: PostReportArtRequest){

        val postReportArtRetrofitInterface = ApplicationClass.sRetrofit.create(
            ArtDetailRetrofitInterface::class.java)
        postReportArtRetrofitInterface.postReportArt(userId, artId, postReportArtRequest).enqueue(object :
            Callback<PostReportArtResponse> {
            override fun onResponse(call: Call<PostReportArtResponse>, response: Response<PostReportArtResponse>) {
                view.onPostReportArtSuccess(response.body() as PostReportArtResponse)
            }

            override fun onFailure(call: Call<PostReportArtResponse>, t: Throwable) {
                view.onPostReportArtFailure(t.message ?: "통신 오류")
            }
        })
    }

}