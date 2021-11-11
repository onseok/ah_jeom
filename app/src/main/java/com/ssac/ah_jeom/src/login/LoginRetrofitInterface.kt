package com.ssac.ah_jeom.src.login

import com.ssac.ah_jeom.src.login.models.PostLoginRequest
import com.ssac.ah_jeom.src.login.models.PostLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginRetrofitInterface {

    @POST("/app/users/{corporation}")
    fun postLogin(@Path("corporation") corporation: String, @Body params: PostLoginRequest): Call<PostLoginResponse>

}