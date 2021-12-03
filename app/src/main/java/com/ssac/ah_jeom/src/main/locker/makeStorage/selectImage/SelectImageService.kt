package com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.models.GetSelectImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectImageService(val view: SelectImageActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetSelectImage(cursor: String?){

        val getSelectImageRetrofitInterface = ApplicationClass.sRetrofit.create(
            SelectImageRetrofitInterface::class.java)
        getSelectImageRetrofitInterface.getSelectImage(userId, cursor).enqueue(object :
            Callback<GetSelectImageResponse> {
            override fun onResponse(call: Call<GetSelectImageResponse>, response: Response<GetSelectImageResponse>) {
                view.onGetSelectImageSuccess(response.body() as GetSelectImageResponse)
            }

            override fun onFailure(call: Call<GetSelectImageResponse>, t: Throwable) {
                view.onGetSelectImageFailure(t.message ?: "통신 오류")
            }
        })
    }

}