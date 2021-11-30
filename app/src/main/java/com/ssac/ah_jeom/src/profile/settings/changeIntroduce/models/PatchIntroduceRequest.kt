package com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models

import com.google.gson.annotations.SerializedName

data class PatchIntroduceRequest(
    @SerializedName("summary") val summary: String
)