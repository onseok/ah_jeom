package com.ssac.ah_jeom.src.main.subscribe.bestArtist.models

import com.google.gson.annotations.SerializedName

data class ResultGetBestArtist(
    @SerializedName("best") val best: ArrayList<ResultGetBestArtistDetail>
)