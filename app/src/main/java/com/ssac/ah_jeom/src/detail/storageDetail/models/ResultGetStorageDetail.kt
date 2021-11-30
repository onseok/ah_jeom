package com.ssac.ah_jeom.src.detail.storageDetail.models

import com.google.gson.annotations.SerializedName

data class ResultGetStorageDetail(
    @SerializedName("top") val top: ArrayList<ResultGetStorageDetailTop>,
    @SerializedName("imgs") val imgs: ArrayList<ResultGetStorageDetailImages>,
    @SerializedName("nextCs") val nextCs: String,
    @SerializedName("hasMore") val hasMore: Boolean
)