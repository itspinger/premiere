package com.premiere.modules

import com.premiere.api.PremiereApi
import com.premiere.api.createPremiereApi
import com.premiere.repository.ConfigRepository
import com.premiere.repository.GenresRepository
import com.premiere.repository.MoviesRepository
import com.premiere.repository.impl.ConfigRepositoryImpl
import com.premiere.repository.impl.GenresRepositoryImpl
import com.premiere.repository.impl.MoviesRepositoryImpl
import com.premiere.ui.movies.details.MovieDetailsViewModel
import com.premiere.ui.movies.filter.FilterMoviesViewModel
import com.premiere.ui.movies.list.MoviesListViewModel
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

const val API_URL = "https://rma.finlab.rs/"

val premiereModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
        }
    }

    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(get<Json>())
            }
        }
    }

    single<Ktorfit> {
        Ktorfit.Builder()
            .baseUrl(API_URL)
            .httpClient(get<HttpClient>())
            .build()
    }

    single<PremiereApi> {
        get<Ktorfit>().createPremiereApi()
    }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get())
    }

    single<GenresRepository> {
        GenresRepositoryImpl(get())
    }

    single<ConfigRepository> {
        ConfigRepositoryImpl(get())
    }

    viewModelOf(::MoviesListViewModel)

    viewModel { parameters ->
        FilterMoviesViewModel(
            initialFilters = parameters.get(),
            genresRepository = get()
        )
    }

    viewModel { parameters ->
        MovieDetailsViewModel(
            imdbId = parameters.get(),
            moviesRepository = get()
        )
    }
}
