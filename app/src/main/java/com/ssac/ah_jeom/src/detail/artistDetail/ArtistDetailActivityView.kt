package com.ssac.ah_jeom.src.detail.artistDetail

import com.ssac.ah_jeom.src.detail.artistDetail.models.GetArtistDetailResponse


interface ArtistDetailActivityView {

    fun onGetArtistDetailSuccess(response: GetArtistDetailResponse)

    fun onGetArtistDetailFailure(message: String)

}