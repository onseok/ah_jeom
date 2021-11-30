package com.ssac.ah_jeom.src.detail.artistDetail.artistArt.models

import com.google.gson.annotations.SerializedName

data class ResultGetArtistArt(
    @SerializedName("imgs") val imgs: ArrayList<ResultGetArtistArtDetail>
)