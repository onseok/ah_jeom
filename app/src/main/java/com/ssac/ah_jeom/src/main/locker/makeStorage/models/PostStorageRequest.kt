package com.ssac.ah_jeom.src.main.locker.makeStorage.models

import com.google.gson.annotations.SerializedName

data class PostStorageRequest(
    @SerializedName("title") val title: String,
    @SerializedName("caption") val caption: String,
    @SerializedName("myimgId") val myimgId: MutableList<Int>
)