package com.ssac.ah_jeom.src.detail.uploadDetail

import com.ssac.ah_jeom.src.detail.uploadDetail.models.PostArtResponse
import com.ssac.ah_jeom.src.detail.uploadDetail.models.PostArtRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface UploadDetailRetrofitInterface {

    // 작품 등록 하기
    @POST("/app/users/{userId}/artwork")
    fun postArt(@Path("userId") userId: Int, @Body postArtBody: PostArtRequest): Call<PostArtResponse>

}