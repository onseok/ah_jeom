package com.ssac.ah_jeom.src.userInfo.keyword

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.userInfo.keyword.models.PostKeywordRequest
import com.ssac.ah_jeom.src.userInfo.keyword.models.PostKeywordResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeywordService(val view: KeywordActivityView) {

    fun tryPostKeyword(userId: Int, params: PostKeywordRequest){

        val postKeywordRetrofitInterface = ApplicationClass.sRetrofit.create(KeywordRetrofitInterface::class.java)
        postKeywordRetrofitInterface.postKeyword(userId, params).enqueue(object :
            Callback<PostKeywordResponse> {
            override fun onResponse(call: Call<PostKeywordResponse>, response: Response<PostKeywordResponse>) {
                view.onPostKeywordSuccess(response.body() as PostKeywordResponse)
            }

            override fun onFailure(call: Call<PostKeywordResponse>, t: Throwable) {
                view.onPostKeywordFailure(t.message ?: "통신 오류")
            }
        })
    }

}