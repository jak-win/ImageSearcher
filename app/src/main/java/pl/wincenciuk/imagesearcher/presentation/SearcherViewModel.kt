package pl.wincenciuk.imagesearcher.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.wincenciuk.imagesearcher.data.a.model.ImageItem
import pl.wincenciuk.imagesearcher.data.a.model.PixabayResponse
import pl.wincenciuk.imagesearcher.data.a.repository.SearcherRepository

class SearcherViewModel(private val repository: SearcherRepository) : ViewModel() {

    private val _imagesData = MutableStateFlow<List<PixabayResponse>>(emptyList())
    val imagesData: Flow<List<PixabayResponse>> = _imagesData.asStateFlow()

    private val _selectedItem = MutableStateFlow<ImageItem?>(null)
    val selectedItem: Flow<ImageItem?> = _selectedItem.asStateFlow()


    suspend fun getImages(q: String) = viewModelScope.launch(Dispatchers.IO) {
        val result = repository.getImages(q)
        result.data?.hits?.let {
            _imagesData.value = listOf(PixabayResponse(hits = it))
        }
        Log.d("ViewModel", result.data.toString())
    }

    fun setSelectedItem(item: ImageItem) {
        _selectedItem.value = item
    }

    init {
        viewModelScope.launch {
            getImages("fruits")
        }
    }

}