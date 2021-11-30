package com.ssac.ah_jeom.src.main.locker.myImage.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyImage(
    @SerializedName("myimg") val myimg: ArrayList<ResultGetMyImageDetail>,
    @SerializedName("nextCs") val nextCs: String,
    @SerializedName("hasMore") val hasMore: Boolean
)