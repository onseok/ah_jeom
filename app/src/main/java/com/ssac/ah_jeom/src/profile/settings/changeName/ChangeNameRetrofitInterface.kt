package com.ssac.ah_jeom.src.profile.settings.changeName

import com.ssac.ah_jeom.src.profile.settings.changeName.models.GetNameResponse
import com.ssac.ah_jeom.src.profile.settings.changeName.models.PatchNameRequest
import com.ssac.ah_jeom.src.profile.settings.changeName.models.PatchNameResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ChangeNameRetrofitInterface {

    // 닉네임 불러오기
    @GET("/app/users/{userId}/mypage/nickname")
    fun getName(@Path("userId") userId: Int): Call<GetNameResponse>

    // 닉네임 변경
    @PATCH("/app/users/{userId}/mypage/nickname")
    fun patchName(@Path("userId") userId: Int, @Body nickname: PatchNameRequest): Call<PatchNameResponse>

}