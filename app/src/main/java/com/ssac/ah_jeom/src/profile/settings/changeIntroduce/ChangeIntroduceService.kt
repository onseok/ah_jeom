package com.ssac.ah_jeom.src.profile.settings.changeIntroduce

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.GetIntroduceResponse
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.PatchIntroduceRequest
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.PatchIntroduceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeIntroduceService(val view: ChangeIntroduceActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetIntroduce(){

        val getIntroduceRetrofitInterface = ApplicationClass.sRetrofit.create(ChangeIntroduceRetrofitInterface::class.java)
        getIntroduceRetrofitInterface.getIntroduce(userId).enqueue(object :
            Callback<GetIntroduceResponse> {
            override fun onResponse(call: Call<GetIntroduceResponse>, response: Response<GetIntroduceResponse>) {
                view.onGetIntroduceSuccess(response.body() as GetIntroduceResponse)
            }

            override fun onFailure(call: Call<GetIntroduceResponse>, t: Throwable) {
                view.onGetIntroduceFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPatchIntroduce(summary: PatchIntroduceRequest){

        val patchIntroduceRetrofitInterface = ApplicationClass.sRetrofit.create(ChangeIntroduceRetrofitInterface::class.java)
        patchIntroduceRetrofitInterface.patchIntroduce(userId, summary).enqueue(object :
            Callback<PatchIntroduceResponse> {
            override fun onResponse(call: Call<PatchIntroduceResponse>, response: Response<PatchIntroduceResponse>) {
                view.onPatchIntroduceSuccess(response.body() as PatchIntroduceResponse)
            }

            override fun onFailure(call: Call<PatchIntroduceResponse>, t: Throwable) {
                view.onPatchIntroduceFailure(t.message ?: "통신 오류")
            }
        })
    }

}