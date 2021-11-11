package com.ssac.ah_jeom.src.userInfo.keyword

import com.ssac.ah_jeom.src.userInfo.keyword.models.PostKeywordRequest
import com.ssac.ah_jeom.src.userInfo.keyword.models.PostKeywordResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface KeywordRetrofitInterface {

    @POST("/app/users/{userId}/keyword")
    fun postKeyword(@Path("userId") userId: Int, @Body params: PostKeywordRequest): Call<PostKeywordResponse>

}