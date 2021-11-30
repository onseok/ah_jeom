package com.ssac.ah_jeom.src.detail.artDetail

import com.ssac.ah_jeom.src.detail.artDetail.models.GetArtDetailResponse
import com.ssac.ah_jeom.src.detail.artDetail.models.PostDownloadImageResponse

interface ArtDetailActivityView {

    fun onGetArtDetailSuccess(response: GetArtDetailResponse)

    fun onGetArtDetailFailure(message: String)

    fun onPostDownloadImageSuccess(response: PostDownloadImageResponse)

    fun onPostDownloadImageFailure(message: String)

}