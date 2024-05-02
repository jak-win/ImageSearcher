package pl.wincenciuk.imagesearcher.data.a.repository

import pl.wincenciuk.imagesearcher.data.a.model.PixabayResponse
import pl.wincenciuk.imagesearcher.data.a.service.ApiService
import pl.wincenciuk.imagesearcher.utils.Constants
import pl.wincenciuk.imagesearcher.utils.Resource
import java.lang.Exception

class SearcherRepositoryImpl(private val apiService: ApiService) : SearcherRepository {

    override suspend fun getImages(q: String): Resource<PixabayResponse> {
        return try {
            val result = apiService.getImages(q, Constants.API_KEY, "photo")
            Resource.Success(data = result)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}