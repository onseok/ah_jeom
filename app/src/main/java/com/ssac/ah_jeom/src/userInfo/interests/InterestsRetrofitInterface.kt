package com.ssac.ah_jeom.src.userInfo.interests

import com.ssac.ah_jeom.src.userInfo.interests.models.PostInterestsRequest
import com.ssac.ah_jeom.src.userInfo.interests.models.PostInterestsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface InterestsRetrofitInterface {

    @POST("/app/users/{userId}/field")
    fun postInterests(@Path("userId") userId: Int, @Body params: PostInterestsRequest): Call<PostInterestsResponse>

}