package com.ssac.ah_jeom.src.detail.artDetail

import com.ssac.ah_jeom.src.detail.artDetail.models.GetArtDetailResponse

interface ArtDetailActivityView {

    fun onGetArtDetailSuccess(response: GetArtDetailResponse)

    fun onGetArtDetailFailure(message: String)

}