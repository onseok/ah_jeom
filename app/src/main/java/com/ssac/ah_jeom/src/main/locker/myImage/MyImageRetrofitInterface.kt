package com.ssac.ah_jeom.src.main.locker.myImage

import com.ssac.ah_jeom.src.main.locker.myImage.models.GetMyImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyImageRetrofitInterface {

    @GET("/app/users/{userId}/myimg")
    fun getMyImage(@Path("userId") userId: Int, @Query("cursor") cursor: String?): Call<GetMyImageResponse>

}