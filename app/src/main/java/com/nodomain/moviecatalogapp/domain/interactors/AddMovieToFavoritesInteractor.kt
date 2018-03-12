package com.nodomain.moviecatalogapp.domain.interactors


import android.os.Handler
import com.nodomain.moviecatalogapp.data.repositories.MovieRepository
import com.nodomain.moviecatalogapp.model.Movie
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.ExecutorService
import javax.inject.Inject


class AddMovieToFavoritesInteractor @Inject constructor(
        executorService: ExecutorService,
        mainThreadHandler: Handler,
        eventBus: EventBus,
        private val movieRepository: MovieRepository)
    : Interactor<Movie>(executorService, mainThreadHandler, eventBus) {

    override fun execute(args: Movie) {
        inBackground {
            movieRepository.addMovieToFavorites(args)
            val favoriteMarkedMovie = args.copy(isFavorite = true)
            onMainThread {
                postStickyEvent(AddMovieToFavoritesFinishEvent(favoriteMarkedMovie))
            }
        }
    }
}

class AddMovieToFavoritesFinishEvent(val movie: Movie)