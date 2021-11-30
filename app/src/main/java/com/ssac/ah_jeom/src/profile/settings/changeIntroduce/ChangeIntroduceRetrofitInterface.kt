package com.ssac.ah_jeom.src.profile.settings.changeIntroduce

import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.GetIntroduceResponse
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.PatchIntroduceRequest
import com.ssac.ah_jeom.src.profile.settings.changeIntroduce.models.PatchIntroduceResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ChangeIntroduceRetrofitInterface {
    
    // 자기소개 불러오기
    @GET("/app/users/{userId}/mypage/summary")
    fun getIntroduce(@Path("userId") userId: Int): Call<GetIntroduceResponse>

    // 자기소개 변경
    @PATCH("/app/users/{userId}/mypage/summary")
    fun patchIntroduce(@Path("userId") userId: Int, @Body summary: PatchIntroduceRequest): Call<PatchIntroduceResponse>

}