package com.ssac.ah_jeom.src.userInfo.interests.models

import com.google.gson.annotations.SerializedName

data class PostInterestsRequest(
    @SerializedName("fieldId") val fieldId: MutableList<Int>
)