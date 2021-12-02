package com.ssac.ah_jeom.src.profile.myStorage

import com.ssac.ah_jeom.src.profile.myStorage.models.GetMyStorageResponse
import com.ssac.ah_jeom.src.profile.myStorage.models.PatchMyStorageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface MyStorageRetrofitInterface {

    @GET("/app/users/{userId}/mystorage")
    fun getMyStorage(@Path("userId") userId: Int): Call<GetMyStorageResponse>

    @PATCH("/app/users/{userId}/storage/{storageId}")
    fun patchMyStorage(@Path("userId") userId: Int, @Path("storageId") storageId: Int): Call<PatchMyStorageResponse>

}