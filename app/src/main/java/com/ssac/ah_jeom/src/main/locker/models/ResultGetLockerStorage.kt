package com.ssac.ah_jeom.src.main.locker.models

import com.google.gson.annotations.SerializedName

data class ResultGetLockerStorage(
    @SerializedName("img") val img: String,
    @SerializedName("storageId") val storageId: Int
)