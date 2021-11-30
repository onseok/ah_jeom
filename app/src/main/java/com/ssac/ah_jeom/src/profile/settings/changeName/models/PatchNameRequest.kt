package com.ssac.ah_jeom.src.profile.settings.changeName.models

import com.google.gson.annotations.SerializedName

data class PatchNameRequest(
    @SerializedName("nickname") val nickname: String
)