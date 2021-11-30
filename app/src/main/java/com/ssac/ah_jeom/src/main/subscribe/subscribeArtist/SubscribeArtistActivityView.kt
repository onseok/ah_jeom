package com.ssac.ah_jeom.src.main.subscribe.subscribeArtist

import com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.models.GetSubscribeArtistResponse

interface SubscribeArtistActivityView {

    fun onGetSubscribeArtistSuccess(response: GetSubscribeArtistResponse)

    fun onGetSubscribeArtistFailure(message: String)

}