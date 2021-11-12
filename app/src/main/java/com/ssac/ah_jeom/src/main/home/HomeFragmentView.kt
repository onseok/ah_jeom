package com.ssac.ah_jeom.src.main.home

import com.ssac.ah_jeom.src.main.home.models.GetHomeResponse

interface HomeFragmentView {

    fun onGetHomeSuccess(response: GetHomeResponse)

    fun onGetHomeFailure(message: String)

}