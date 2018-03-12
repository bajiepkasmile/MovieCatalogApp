package com.nodomain.moviecatalogapp.domain.interactors


import android.os.Handler
import com.nodomain.moviecatalogapp.data.repositories.MovieRepository
import com.nodomain.moviecatalogapp.domain.exceptions.NetworkIsNotAvailableException
import com.nodomain.moviecatalogapp.domain.utils.NetworkUtil
import com.nodomain.moviecatalogapp.model.MoviePage
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.ExecutorService
import javax.inject.Inject


class GetNextMoviePageInteractor @Inject constructor(
        executorService: ExecutorService,
        mainThreadHandler: Handler,
        eventBus: EventBus,
        private val networkUtil: NetworkUtil,
        private val movieRepository: MovieRepository)
    : Interactor<GetMoviesArgs>(executorService, mainThreadHandler, eventBus) {

    override fun execute(args: GetMoviesArgs) {
        val networkIsAvailable = networkUtil.isNetworkAvailable()
        if (networkIsAvailable) {
            postStickyEvent(GetMoviesAsyncStartEvent(args.moviePage))
            getMoviesAsync(args.moviePage)
        } else
            postStickyEvent(GetMoviesFailureEvent(NetworkIsNotAvailableException(), args.moviePage))
    }

    private fun getMoviesAsync(moviePage: MoviePage) {
        inBackground {
            try {
                val nextMoviePage = movieRepository.getMoviePage(moviePage.filterStr, moviePage.number)
                onMainThread { postStickyEvent(GetMoviesSuccessEvent(nextMoviePage)) }
            } catch (e: Exception) {
                onMainThread { postStickyEvent(GetMoviesFailureEvent(e, moviePage)) }
            }
        }
    }
}

class GetMoviesArgs(val moviePage: MoviePage)

class GetMoviesAsyncStartEvent(val moviePage: MoviePage)

class GetMoviesSuccessEvent(val moviePage: MoviePage)

class GetMoviesFailureEvent(val exception: Exception, val moviePage: MoviePage)