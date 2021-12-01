package com.ssac.ah_jeom.src.detail.reviewDetail

import com.ssac.ah_jeom.src.detail.reviewDetail.models.PostReviewResponse

interface ReviewDetailActivityView {

    fun onPostReviewSuccess(response: PostReviewResponse)

    fun onPostReviewFailure(message: String)

}