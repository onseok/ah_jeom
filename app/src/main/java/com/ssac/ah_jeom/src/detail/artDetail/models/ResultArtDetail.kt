package com.ssac.ah_jeom.src.detail.artDetail.models

import com.google.gson.annotations.SerializedName

data class ResultArtDetail(
    @SerializedName("art") val art: ArrayList<ResultArtDetailArt>,
    @SerializedName("review") val review: ArrayList<ResultArtDetailReview>
)