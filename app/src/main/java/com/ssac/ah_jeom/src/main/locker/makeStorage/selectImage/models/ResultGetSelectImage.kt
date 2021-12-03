package com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.models

import com.google.gson.annotations.SerializedName

data class ResultGetSelectImage(
    @SerializedName("myimg") val myimg: ArrayList<ResultGetSelectImageDetail>,
    @SerializedName("nextCs") val nextCs: String,
    @SerializedName("hasMore") val hasMore: Boolean
)