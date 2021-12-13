package com.ssac.ah_jeom.src.detail.artistDetail.artistReview

import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.GetArtistReviewResponse
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.PostReportReviewResponse

interface ArtistReviewActivityView {

    fun onGetArtistReviewSuccess(response: GetArtistReviewResponse)

    fun onGetArtistReviewFailure(message: String)

}