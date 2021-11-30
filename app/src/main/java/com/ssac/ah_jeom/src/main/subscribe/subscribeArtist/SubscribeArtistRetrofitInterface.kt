package com.ssac.ah_jeom.src.main.subscribe.subscribeArtist
import com.ssac.ah_jeom.src.main.subscribe.subscribeArtist.models.GetSubscribeArtistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SubscribeArtistRetrofitInterface {

    @GET("/app/users/{userId}/subscribe/list")
    fun getSubscribeArtist(@Path("userId") userId: Int, @Query("cursor") cursor: String?): Call<GetSubscribeArtistResponse>

}