package com.ssac.ah_jeom.src.login

import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.src.login.models.PostLoginRequest
import com.ssac.ah_jeom.src.login.models.PostLoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view: LoginActivityView) {

    fun tryPostLogin(corporation: String, params: PostLoginRequest){

        val postLoginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        postLoginRetrofitInterface.postLogin(corporation, params).enqueue(object :
            Callback<PostLoginResponse> {
            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                view.onPostLoginSuccess(response.body() as PostLoginResponse)
            }

            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                view.onPostLoginFailure(t.message ?: "통신 오류")
            }
        })
    }

}