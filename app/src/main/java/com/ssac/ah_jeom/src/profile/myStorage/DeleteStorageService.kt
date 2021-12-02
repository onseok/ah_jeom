package com.ssac.ah_jeom.src.profile.myStorage

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.profile.myStorage.models.PatchMyStorageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteStorageService(val view: DeleteStorageActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryPatchMyStorage(storageId: Int){

        val patchMyStorageRetrofitInterface = ApplicationClass.sRetrofit.create(
            MyStorageRetrofitInterface::class.java)
        patchMyStorageRetrofitInterface.patchMyStorage(userId, storageId).enqueue(object :
            Callback<PatchMyStorageResponse> {
            override fun onResponse(call: Call<PatchMyStorageResponse>, response: Response<PatchMyStorageResponse>) {
                view.onPatchMyStorageSuccess(response.body() as PatchMyStorageResponse)
            }

            override fun onFailure(call: Call<PatchMyStorageResponse>, t: Throwable) {
                view.onPatchMyStorageFailure(t.message ?: "통신 오류")
            }
        })
    }

}