package com.ssac.ah_jeom.src.main.peek.bestStorage.models

import com.google.gson.annotations.SerializedName

data class ResultGetBestStorage(
    @SerializedName("best") val best: ArrayList<ResultGetBestStorageBest>,
    @SerializedName("nextCs") val nextCs: String,
    @SerializedName("hasMore") val hasMore: Boolean
)