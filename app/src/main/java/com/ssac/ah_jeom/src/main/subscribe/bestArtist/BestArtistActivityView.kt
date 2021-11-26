package com.ssac.ah_jeom.src.main.subscribe.bestArtist

import com.ssac.ah_jeom.src.main.subscribe.bestArtist.models.GetBestArtistResponse

interface BestArtistActivityView {

    fun onGetBestArtistSuccess(response: GetBestArtistResponse)

    fun onGetBestArtistFailure(message: String)

}