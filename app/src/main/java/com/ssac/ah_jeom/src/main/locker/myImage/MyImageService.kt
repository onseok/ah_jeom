package com.ssac.ah_jeom.src.main.locker.myImage

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.locker.myImage.models.GetMyImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyImageService(val view: MyImageActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetMyImage(cursor: String?){

        val getMyImageRetrofitInterface = ApplicationClass.sRetrofit.create(
            MyImageRetrofitInterface::class.java)
        getMyImageRetrofitInterface.getMyImage(userId, cursor).enqueue(object :
            Callback<GetMyImageResponse> {
            override fun onResponse(call: Call<GetMyImageResponse>, response: Response<GetMyImageResponse>) {
                view.onGetMyImageSuccess(response.body() as GetMyImageResponse)
            }

            override fun onFailure(call: Call<GetMyImageResponse>, t: Throwable) {
                view.onGetMyImageFailure(t.message ?: "통신 오류")
            }
        })
    }

}