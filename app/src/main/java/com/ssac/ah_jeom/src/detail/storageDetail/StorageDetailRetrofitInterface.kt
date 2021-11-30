package com.ssac.ah_jeom.src.detail.storageDetail

import com.ssac.ah_jeom.src.detail.storageDetail.models.GetStorageDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StorageDetailRetrofitInterface {

    @GET("/app/users/{userId}/storage/{storageId}}")
    fun getStorageDetail(@Path("userId") userId: Int, @Path("storageId") storageId: Int): Call<GetStorageDetailResponse>

}