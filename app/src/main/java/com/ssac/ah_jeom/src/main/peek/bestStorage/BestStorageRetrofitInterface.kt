package com.ssac.ah_jeom.src.main.peek.bestStorage

import com.ssac.ah_jeom.src.main.peek.bestStorage.models.GetBestStorageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BestStorageRetrofitInterface {

    @GET("/app/users/{userId}/lookaround/best")
    fun getBestStorage(@Path("userId") userId: Int, @Query("cursor") cursor: String?): Call<GetBestStorageResponse>

}