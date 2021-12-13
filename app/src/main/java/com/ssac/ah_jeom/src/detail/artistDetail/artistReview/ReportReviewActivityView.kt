package com.ssac.ah_jeom.src.detail.artistDetail.artistReview

import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.PostReportReviewResponse

interface ReportReviewActivityView {

    fun onPostReportReviewSuccess(response: PostReportReviewResponse)

    fun onPostReportReviewFailure(message: String)

}

