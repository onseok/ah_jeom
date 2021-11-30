package com.ssac.ah_jeom.src.profile.myPeek

import com.ssac.ah_jeom.src.profile.myPeek.models.GetMyPeekResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyPeekRetrofitInterface {

    @GET("/app/users/{userId}/savestorage")
    fun getMyPeek(@Path("userId") userId: Int, @Query("cursor") cursor: String?): Call<GetMyPeekResponse>

}