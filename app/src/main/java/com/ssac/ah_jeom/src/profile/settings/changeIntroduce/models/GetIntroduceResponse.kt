package com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models

import com.google.gson.annotations.SerializedName

data class GetIntroduceResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ArrayList<ResultGetIntroduce>?
)