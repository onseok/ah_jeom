package com.ssac.ah_jeom.src.profile.settings.changeImage

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.profile.settings.changeImage.models.GetImageResponse
import com.ssac.ah_jeom.src.profile.settings.changeImage.models.PatchImageRequest
import com.ssac.ah_jeom.src.profile.settings.changeImage.models.PatchImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeImageService(val view: ChangeImageActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetImage(){

        val getImageRetrofitInterface = ApplicationClass.sRetrofit.create(
            ChangeImageRetrofitInterface::class.java)
        getImageRetrofitInterface.getImage(userId).enqueue(object :
            Callback<GetImageResponse> {
            override fun onResponse(call: Call<GetImageResponse>, response: Response<GetImageResponse>) {
                view.onGetImageSuccess(response.body() as GetImageResponse)
            }

            override fun onFailure(call: Call<GetImageResponse>, t: Throwable) {
                view.onGetImageFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPatchImage(patchImageRequest: PatchImageRequest){

        val patchImageRetrofitInterface = ApplicationClass.sRetrofit.create(
            ChangeImageRetrofitInterface::class.java)
        patchImageRetrofitInterface.patchImage(userId, patchImageRequest).enqueue(object :
            Callback<PatchImageResponse> {
            override fun onResponse(call: Call<PatchImageResponse>, response: Response<PatchImageResponse>) {
                view.onPatchImageSuccess(response.body() as PatchImageResponse)
            }

            override fun onFailure(call: Call<PatchImageResponse>, t: Throwable) {
                view.onPatchImageFailure(t.message ?: "통신 오류")
            }
        })
    }

}