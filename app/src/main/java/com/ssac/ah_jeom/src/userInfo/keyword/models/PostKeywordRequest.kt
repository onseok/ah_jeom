package com.ssac.ah_jeom.src.userInfo.keyword.models

import com.google.gson.annotations.SerializedName

data class PostKeywordRequest(
    @SerializedName("fieldId") val fieldId: MutableList<Int>
)