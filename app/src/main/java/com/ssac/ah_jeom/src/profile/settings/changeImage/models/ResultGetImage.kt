package com.ssac.ah_jeom.src.profile.settings.changeImage.models

import com.google.gson.annotations.SerializedName

data class ResultGetImage(
    @SerializedName("profile") val profile: String
)