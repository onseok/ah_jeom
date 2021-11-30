package com.ssac.ah_jeom.src.detail.storageDetail

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.detail.storageDetail.models.GetStorageDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StorageDetailService(val view: StorageDetailActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

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

}