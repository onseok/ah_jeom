package com.ssac.ah_jeom.src.detail.uploadDetail.models

import com.google.gson.annotations.SerializedName

data class PostArtRequest(
    @SerializedName("title") val title: String,
    @SerializedName("caption") val caption: String,
    @SerializedName("img") val img: String,
    @SerializedName("price") val price: Int,
    @SerializedName("link") val link: String,
    @SerializedName("fieldId") val fieldId: MutableList<Int>,
    @SerializedName("kwId") val kwId: MutableList<Int>
)