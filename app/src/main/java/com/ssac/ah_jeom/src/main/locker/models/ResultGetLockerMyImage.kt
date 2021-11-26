package com.ssac.ah_jeom.src.main.locker.models

import com.google.gson.annotations.SerializedName

data class ResultGetLockerMyImage(
    @SerializedName("img") val img: String,
    @SerializedName("icount") val icount: Int,
    @SerializedName("artId") val artId: Int,
    @SerializedName("myimgId") val myimgId: Int
)