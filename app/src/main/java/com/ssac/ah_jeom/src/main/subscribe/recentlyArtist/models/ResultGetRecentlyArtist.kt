package com.ssac.ah_jeom.src.main.subscribe.recentlyArtist.models

import com.google.gson.annotations.SerializedName

data class ResultGetRecentlyArtist(
    @SerializedName("sub") val sub: ArrayList<ResultGetRecentlyArtistDetail>
)