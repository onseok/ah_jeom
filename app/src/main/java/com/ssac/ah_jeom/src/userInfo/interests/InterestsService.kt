package com.ssac.ah_jeom.src.userInfo.interests

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.userInfo.interests.models.PostInterestsRequest
import com.ssac.ah_jeom.src.userInfo.interests.models.PostInterestsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InterestsService(val view: InterestsActivityView) {

    fun tryPostInterests(userId: Int, params: PostInterestsRequest){

        val postInterestsRetrofitInterface = ApplicationClass.sRetrofit.create(InterestsRetrofitInterface::class.java)
        postInterestsRetrofitInterface.postInterests(userId, params).enqueue(object :
            Callback<PostInterestsResponse> {
            override fun onResponse(call: Call<PostInterestsResponse>, response: Response<PostInterestsResponse>) {
                view.onPostInterestsSuccess(response.body() as PostInterestsResponse)
            }

            override fun onFailure(call: Call<PostInterestsResponse>, t: Throwable) {
                view.onPostInterestsFailure(t.message ?: "통신 오류")
            }
        })
    }

}