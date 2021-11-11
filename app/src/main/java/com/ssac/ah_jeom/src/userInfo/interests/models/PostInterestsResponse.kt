package com.ssac.ah_jeom.src.userInfo.interests.models

import com.google.gson.annotations.SerializedName
import com.ssac.ah_jeom.src.login.models.ResultPostLogin

data class PostInterestsResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)