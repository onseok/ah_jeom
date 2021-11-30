package com.ssac.ah_jeom.src.profile.myArt

import com.ssac.ah_jeom.src.profile.myArt.models.GetMyArtResponse

interface MyArtActivityView {

    fun onGetMyArtSuccess(response: GetMyArtResponse)

    fun onGetMyArtFailure(message: String)

}