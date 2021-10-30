package com.ssac.ah_jeom.src.main.home

import com.ssac.ah_jeom.src.main.home.models.SignUpResponse
import com.ssac.ah_jeom.src.main.home.models.UserResponse

interface HomeFragmentView {

    fun onGetUserSuccess(response: UserResponse)

    fun onGetUserFailure(message: String)

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)
}