package com.ssac.ah_jeom.src.profile.settings.changeImage

import com.ssac.ah_jeom.src.profile.settings.changeImage.models.GetImageResponse
import com.ssac.ah_jeom.src.profile.settings.changeImage.models.PatchImageRequest
import com.ssac.ah_jeom.src.profile.settings.changeImage.models.PatchImageResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ChangeImageRetrofitInterface {

    // 프사 불러오기
    @GET("/app/users/{userId}/mypage/profile")
    fun getImage(@Path("userId") userId: Int): Call<GetImageResponse>

    // 프사 변경하기
    @PATCH("/app/users/{userId}/mypage/profile")
    fun patchImage(@Path("userId") userId: Int, @Body patchImageRequest: PatchImageRequest): Call<PatchImageResponse>

}