package com.ssac.ah_jeom.src.detail.storageDetail

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.detail.storageDetail.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StorageDetailService(val view: StorageDetailActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    // 보관함 정보 조회
    fun tryGetStorageDetail(storageId: Int){

        val getStorageDetailRetrofitInterface = ApplicationClass.sRetrofit.create(
            StorageDetailRetrofitInterface::class.java)
        getStorageDetailRetrofitInterface.getStorageDetail(userId, storageId).enqueue(object :
            Callback<GetStorageDetailResponse> {
            override fun onResponse(call: Call<GetStorageDetailResponse>, response: Response<GetStorageDetailResponse>) {
                view.onGetStorageDetailSuccess(response.body() as GetStorageDetailResponse)
            }

            override fun onFailure(call: Call<GetStorageDetailResponse>, t: Throwable) {
                view.onGetStorageDetailFailure(t.message ?: "통신 오류")
            }
        })
    }

    // 보관함 좋아요
    fun tryPostStorageLike(storageId: Int){

        val postStorageLikeRetrofitInterface = ApplicationClass.sRetrofit.create(
            StorageDetailRetrofitInterface::class.java)
        postStorageLikeRetrofitInterface.postStorageLike(userId, storageId).enqueue(object :
            Callback<PostStorageLikeResponse> {
            override fun onResponse(call: Call<PostStorageLikeResponse>, response: Response<PostStorageLikeResponse>) {
                view.onPostStorageLikeSuccess(response.body() as PostStorageLikeResponse)
            }

            override fun onFailure(call: Call<PostStorageLikeResponse>, t: Throwable) {
                view.onPostStorageLikeFailure(t.message ?: "통신 오류")
            }
        })
    }

    // 보관함 좋아요 취소
    fun tryPatchStorageLike(storageId: Int){

        val patchStorageLikeRetrofitInterface = ApplicationClass.sRetrofit.create(
            StorageDetailRetrofitInterface::class.java)
        patchStorageLikeRetrofitInterface.patchStorageLike(userId, storageId).enqueue(object :
            Callback<PatchStorageLikeResponse> {
            override fun onResponse(call: Call<PatchStorageLikeResponse>, response: Response<PatchStorageLikeResponse>) {
                view.onPatchStorageLikeSuccess(response.body() as PatchStorageLikeResponse)
            }

            override fun onFailure(call: Call<PatchStorageLikeResponse>, t: Throwable) {
                view.onPatchStorageLikeFailure(t.message ?: "통신 오류")
            }
        })
    }

    // 보관함 저장
    fun tryPostStorageSave(storageId: Int){

        val postStorageSaveRetrofitInterface = ApplicationClass.sRetrofit.create(
            StorageDetailRetrofitInterface::class.java)
        postStorageSaveRetrofitInterface.postStorageSave(userId, storageId).enqueue(object :
            Callback<PostStorageSaveResponse> {
            override fun onResponse(call: Call<PostStorageSaveResponse>, response: Response<PostStorageSaveResponse>) {
                view.onPostStorageSaveSuccess(response.body() as PostStorageSaveResponse)
            }

            override fun onFailure(call: Call<PostStorageSaveResponse>, t: Throwable) {
                view.onPostStorageSaveFailure(t.message ?: "통신 오류")
            }
        })
    }
    
    // 보관함 저장 취소
    fun tryPatchStorageSave(storageId: Int){

        val patchStorageSaveRetrofitInterface = ApplicationClass.sRetrofit.create(
            StorageDetailRetrofitInterface::class.java)
        patchStorageSaveRetrofitInterface.patchStorageSave(userId, storageId).enqueue(object :
            Callback<PatchStorageSaveResponse> {
            override fun onResponse(call: Call<PatchStorageSaveResponse>, response: Response<PatchStorageSaveResponse>) {
                view.onPatchStorageSaveSuccess(response.body() as PatchStorageSaveResponse)
            }

            override fun onFailure(call: Call<PatchStorageSaveResponse>, t: Throwable) {
                view.onPatchStorageSaveFailure(t.message ?: "통신 오류")
            }
        })
    }

}