package pl.wincenciuk.imagesearcher.data.a.model

data class PixabayResponse(
    val hits: List<ImageItem> = listOf(),
    val total: Int? = null,
    val totalHits: Int? = null
)

data class ImageItem(
    val id: Int? = null,
    val pageURL: String? = null,
    val type: String? = null,
    val tags: String? = null,
    val previewURL: String? = null,
    val previewWidth: Int? = null,
    val webformatHeight: Int? = null,
    val previewHeight: Int? = null,
    val webformatURL: String? = null,
    val userImageURL: String? = null,
    val comments: Int? = null,
    val imageHeight: Int? = null,
    val imageWidth: Int? = null,
    val downloads: Int? = null,
    val collections: Int? = null,
    val userId: Int? = null,
    val largeImageURL: String? = null,
    val imageSize: Int? = null,
    val webformatWidth: Int? = null,
    val user: String? = null,
    val views: Int? = null,
    val likes: Int? = null,
)
