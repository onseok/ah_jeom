package com.ssac.ah_jeom.src.main.subscribe.recentlyArtist

import com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.models.GetRecentlyArtistResponse


interface RecentlyArtistActivityView {

    fun onGetRecentlyArtistSuccess(response: GetRecentlyArtistResponse)

    fun onGetRecentlyArtistFailure(message: String)

}