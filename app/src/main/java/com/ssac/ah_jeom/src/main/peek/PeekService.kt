package com.ssac.ah_jeom.src.main.peek

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.main.peek.models.GetPeekResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeekService(val view: PeekFragmentView) {

    val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

    fun tryGetPeek(){

        val getPeekRetrofitInterface = ApplicationClass.sRetrofit.create(
            PeekRetrofitInterface::class.java)
        getPeekRetrofitInterface.getPeek(userId).enqueue(object :
            Callback<GetPeekResponse> {
            override fun onResponse(call: Call<GetPeekResponse>, response: Response<GetPeekResponse>) {
                view.onGetPeekSuccess(response.body() as GetPeekResponse)
            }

            override fun onFailure(call: Call<GetPeekResponse>, t: Throwable) {
                view.onGetPeekFailure(t.message ?: "통신 오류")
            }
        })
    }

}