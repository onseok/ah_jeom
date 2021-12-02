package com.ssac.ah_jeom.src.detail.storageDetail.models

import com.google.gson.annotations.SerializedName

data class ResultGetStorageDetailTop(
    @SerializedName("storageId") val storageId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("caption") val caption: String,
    @SerializedName("img") val img: String,
    @SerializedName("heart") val heart: Int,
    @SerializedName("save") val save: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("store") val store: Int, //옅보기저장했는지여부(1이면저장, 0이면저장안함)
    @SerializedName("likes") val likes: Int, //좋아요눌렀는지여부(1이면좋아요, 0이면좋아요안함)
    @SerializedName("cs") val cs: String
)