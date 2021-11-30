package com.ssac.ah_jeom.src.profile.myStorage.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyStorage(
    @SerializedName("storage") val storage: ArrayList<ResultGetMyStorageDetail>,
    @SerializedName("nextCs") val nextCs: String,
    @SerializedName("hasMore") val hasMore: Boolean
)