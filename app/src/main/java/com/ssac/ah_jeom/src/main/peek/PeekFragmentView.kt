package com.ssac.ah_jeom.src.main.peek

import com.ssac.ah_jeom.src.main.peek.models.GetPeekResponse

interface PeekFragmentView {

    fun onGetPeekSuccess(response: GetPeekResponse)

    fun onGetPeekFailure(message: String)

}