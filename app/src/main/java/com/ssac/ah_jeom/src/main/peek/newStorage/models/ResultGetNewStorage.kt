package com.ssac.ah_jeom.src.main.peek.newStorage.models

import com.google.gson.annotations.SerializedName

data class ResultGetNewStorage(
    @SerializedName("new") val new: ArrayList<ResultGetNewStorageNew>,
    @SerializedName("nextCs") val nextCs: String,
    @SerializedName("hasMore") val hasMore: Boolean
)