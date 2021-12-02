package com.ssac.ah_jeom.src.detail.uploadDetail

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.detail.uploadDetail.models.PostArtRequest
import com.ssac.ah_jeom.src.detail.uploadDetail.models.PostArtResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UploadDetailService(val view: UploadDetailActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryPostArt(postArtBody: PostArtRequest){

        val postArtRetrofitInterface = ApplicationClass.sRetrofit.create(
            UploadDetailRetrofitInterface::class.java)
        postArtRetrofitInterface.postArt(userId, postArtBody).enqueue(object :
            Callback<PostArtResponse> {
            override fun onResponse(call: Call<PostArtResponse>, response: Response<PostArtResponse>) {
                view.onPostArtSuccess(response.body() as PostArtResponse)
            }

            override fun onFailure(call: Call<PostArtResponse>, t: Throwable) {
                view.onPostArtFailure(t.message ?: "통신 오류")
            }
        })
    }

}