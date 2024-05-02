package pl.wincenciuk.imagesearcher.data.a.repository

import pl.wincenciuk.imagesearcher.data.a.model.PixabayResponse
import pl.wincenciuk.imagesearcher.utils.Resource

interface SearcherRepository {
    suspend fun getImages(q: String): Resource<PixabayResponse>
}