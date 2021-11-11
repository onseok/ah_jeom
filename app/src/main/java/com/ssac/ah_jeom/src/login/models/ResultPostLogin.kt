package com.ssac.ah_jeom.src.login.models

import com.google.gson.annotations.SerializedName

data class ResultPostLogin (
    @SerializedName("userId") val userId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("jwt") val jwt: String
)