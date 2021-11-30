package com.ssac.ah_jeom.src.profile.myPeek

import com.ssac.ah_jeom.src.profile.myPeek.models.GetMyPeekResponse

interface MyPeekActivityView {

    fun onGetMyPeekSuccess(response: GetMyPeekResponse)

    fun onGetMyPeekFailure(message: String)

}