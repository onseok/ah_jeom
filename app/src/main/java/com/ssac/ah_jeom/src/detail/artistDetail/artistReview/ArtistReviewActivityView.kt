package com.ssac.ah_jeom.src.detail.artistDetail.artistReview

import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.GetArtistReviewResponse

interface ArtistReviewActivityView {

    fun onGetArtistReviewSuccess(response: GetArtistReviewResponse)

    fun onGetArtistReviewFailure(message: String)

}