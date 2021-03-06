package com.ssac.ah_jeom.src.login.models

import com.google.gson.annotations.SerializedName

data class PostLoginResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultPostLogin?
)