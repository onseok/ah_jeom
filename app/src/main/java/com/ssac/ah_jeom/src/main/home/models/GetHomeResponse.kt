package com.ssac.ah_jeom.src.main.home.models

import com.google.gson.annotations.SerializedName

data class GetHomeResponse(
    @SerializedName("field") val field: ResultFieldAll,
    @SerializedName("kw") val kw: ResultKeywordAll,
    @SerializedName("best") val best: ArrayList<ResultBestArtist>,
    @SerializedName("new") val new: ArrayList<ResultNewArtist>
)