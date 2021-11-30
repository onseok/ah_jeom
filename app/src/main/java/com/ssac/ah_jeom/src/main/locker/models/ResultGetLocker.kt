package com.ssac.ah_jeom.src.main.locker.models

import com.google.gson.annotations.SerializedName

data class ResultGetLocker(
    @SerializedName("storage") val storage: ArrayList<ResultGetLockerStorage>?,
    @SerializedName("myimg") val myimg: ArrayList<ResultGetLockerMyImage>?
)