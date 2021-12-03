package com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage

import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.models.GetSelectImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SelectImageRetrofitInterface {

    @GET("/app/users/{userId}/myimg")
    fun getSelectImage(@Path("userId") userId: Int, @Query("cursor") cursor: String?): Call<GetSelectImageResponse>

}