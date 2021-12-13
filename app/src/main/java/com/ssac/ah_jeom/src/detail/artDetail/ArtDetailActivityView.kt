package com.ssac.ah_jeom.src.detail.artDetail

import com.ssac.ah_jeom.src.detail.artDetail.models.GetArtDetailResponse
import com.ssac.ah_jeom.src.detail.artDetail.models.PostDownloadImageResponse
import com.ssac.ah_jeom.src.detail.artDetail.models.PostReportArtResponse

interface ArtDetailActivityView {

    fun onGetArtDetailSuccess(response: GetArtDetailResponse)

    fun onGetArtDetailFailure(message: String)

    fun onPostDownloadImageSuccess(response: PostDownloadImageResponse)

    fun onPostDownloadImageFailure(message: String)

    fun onPostReportArtSuccess(response: PostReportArtResponse)

    fun onPostReportArtFailure(message: String)

}