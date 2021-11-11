package com.ssac.ah_jeom.src.userInfo.keyword.models

import com.google.gson.annotations.SerializedName

data class PostKeywordResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)