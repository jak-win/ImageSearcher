package pl.wincenciuk.imagesearcher.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.wincenciuk.imagesearcher.data.a.repository.SearcherRepository
import pl.wincenciuk.imagesearcher.data.a.repository.SearcherRepositoryImpl
import pl.wincenciuk.imagesearcher.data.a.service.ApiService
import pl.wincenciuk.imagesearcher.presentation.SearcherViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single<SearcherRepository> {
        SearcherRepositoryImpl(get())
    }

    viewModel {
        SearcherViewModel(get())
    }
}