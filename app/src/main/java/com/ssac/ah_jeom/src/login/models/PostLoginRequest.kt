package com.ssac.ah_jeom.src.login.models

import com.google.gson.annotations.SerializedName

data class PostLoginRequest(
    @SerializedName("access_token") val access_token: String
)