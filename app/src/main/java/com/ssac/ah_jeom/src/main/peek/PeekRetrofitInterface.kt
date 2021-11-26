package com.ssac.ah_jeom.src.main.peek

import com.ssac.ah_jeom.src.main.peek.models.GetPeekResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PeekRetrofitInterface {

    @GET("/app/users/{userId}/lookaround")
    fun getPeek(@Path("userId") userId: Int): Call<GetPeekResponse>

}