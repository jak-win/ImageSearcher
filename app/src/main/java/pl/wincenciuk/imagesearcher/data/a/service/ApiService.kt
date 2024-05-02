package pl.wincenciuk.imagesearcher.data.a.service

import pl.wincenciuk.imagesearcher.data.a.model.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getImages(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("image_type") imageType: String,
    ): PixabayResponse
}