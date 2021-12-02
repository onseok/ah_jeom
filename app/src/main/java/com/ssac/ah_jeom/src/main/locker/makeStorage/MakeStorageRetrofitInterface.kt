package com.ssac.ah_jeom.src.main.locker.makeStorage

import com.ssac.ah_jeom.src.main.locker.makeStorage.models.PostStorageRequest
import com.ssac.ah_jeom.src.main.locker.makeStorage.models.PostStorageResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface MakeStorageRetrofitInterface {

    @GET("/app/users/{userId}/storage")
    fun postStorage(@Path("userId") userId: Int, @Body postStorageRequest: PostStorageRequest): Call<PostStorageResponse>

}