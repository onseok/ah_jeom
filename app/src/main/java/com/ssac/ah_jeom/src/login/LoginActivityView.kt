package com.ssac.ah_jeom.src.login

import com.ssac.ah_jeom.src.login.models.PostLoginResponse

interface LoginActivityView {

    fun onPostLoginSuccess(response: PostLoginResponse)

    fun onPostLoginFailure(message: String)
}