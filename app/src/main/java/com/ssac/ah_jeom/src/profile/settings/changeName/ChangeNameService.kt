package com.ssac.ah_jeom.src.profile.settings.changeName

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.profile.settings.changeName.models.GetNameResponse
import com.ssac.ah_jeom.src.profile.settings.changeName.models.PatchNameRequest
import com.ssac.ah_jeom.src.profile.settings.changeName.models.PatchNameResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeNameService(val view: ChangeNameActivityView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetName(){

        val getNameRetrofitInterface = ApplicationClass.sRetrofit.create(
            ChangeNameRetrofitInterface::class.java)
        getNameRetrofitInterface.getName(userId).enqueue(object :
            Callback<GetNameResponse> {
            override fun onResponse(call: Call<GetNameResponse>, response: Response<GetNameResponse>) {
                view.onGetNameSuccess(response.body() as GetNameResponse)
            }

            override fun onFailure(call: Call<GetNameResponse>, t: Throwable) {
                view.onGetNameFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPatchName(nickname: PatchNameRequest){

        val patchNameRetrofitInterface = ApplicationClass.sRetrofit.create(
            ChangeNameRetrofitInterface::class.java)
        patchNameRetrofitInterface.patchName(userId, nickname).enqueue(object :
            Callback<PatchNameResponse> {
            override fun onResponse(call: Call<PatchNameResponse>, response: Response<PatchNameResponse>) {
                view.onPatchNameSuccess(response.body() as PatchNameResponse)
            }

            override fun onFailure(call: Call<PatchNameResponse>, t: Throwable) {
                view.onPatchNameFailure(t.message ?: "통신 오류")
            }
        })
    }

}