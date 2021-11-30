package com.ssac.ah_jeom.src.main.peek.newStorage

import com.ssac.ah_jeom.src.main.peek.newStorage.models.GetNewStorageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewStorageRetrofitInterface {

    @GET("/app/users/{userId}/lookaround/recent")
    fun getNewStorage(@Path("userId") userId: Int, @Query("cursor") cursor: String?): Call<GetNewStorageResponse>

}