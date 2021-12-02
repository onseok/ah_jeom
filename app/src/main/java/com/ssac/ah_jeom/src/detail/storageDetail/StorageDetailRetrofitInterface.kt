package com.ssac.ah_jeom.src.detail.storageDetail

import com.ssac.ah_jeom.src.detail.storageDetail.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface StorageDetailRetrofitInterface {

    // 보관함 정보 조회
    @GET("/app/users/{userId}/storage/{storageId}}")
    fun getStorageDetail(@Path("userId") userId: Int, @Path("storageId") storageId: Int): Call<GetStorageDetailResponse>

    // 보관함 좋아요
    @POST("/app/users/{userId}/storage/{storageId}/like")
    fun postStorageLike(@Path("userId") userId: Int, @Path("storageId") storageId: Int): Call<PostStorageLikeResponse>

    // 보관함 좋아요 취소
    @PATCH("/app/users/{userId}/storage/{storageId}/like")
    fun patchStorageLike(@Path("userId") userId: Int, @Path("storageId") storageId: Int): Call<PatchStorageLikeResponse>

    // 보관함 저장
    @POST("/app/users/{userId}/storage/{storageId}/save")
    fun postStorageSave(@Path("userId") userId: Int, @Path("storageId") storageId: Int): Call<PostStorageSaveResponse>

    // 보관함 저장 취소
    @PATCH("/app/users/{userId}/storage/{storageId}/save")
    fun patchStorageSave(@Path("userId") userId: Int, @Path("storageId") storageId: Int): Call<PatchStorageSaveResponse>

}