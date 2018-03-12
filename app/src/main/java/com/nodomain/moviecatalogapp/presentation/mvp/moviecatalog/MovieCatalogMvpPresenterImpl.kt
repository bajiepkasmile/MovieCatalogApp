package com.nodomain.moviecatalogapp.presentation.mvp.moviecatalog


import com.nodomain.moviecatalogapp.domain.interactors.*
import com.nodomain.moviecatalogapp.model.Movie
import com.nodomain.moviecatalogapp.model.MoviePage
import com.nodomain.moviecatalogapp.presentation.utils.TimerUtil
import com.nodomain.moviecatalogapp.presentation.mvp.base.MvpPresenterImpl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*
import javax.inject.Inject


private const val SEARCH_DELAY = 1000L


class MovieCatalogMvpPresenterImpl @Inject constructor(
        eventBus: EventBus,
        private val getNextMoviePageInteractor: GetNextMoviePageInteractor,
        private val addMovieToFavoritesInteractor: AddMovieToFavoritesInteractor,
        private val removeMovieFromFavoritesInteractor: RemoveMovieFromFavoritesInteractor,
        private val timerUtil: TimerUtil)
    : MvpPresenterImpl<MovieCatalogMvpView>(eventBus), MovieCatalogMvpPresenter {

    private var timer = Timer()
    private var currentPage: MoviePage = MoviePage(number = 0)

    override fun loadMoreMovies() {
        timer.cancel()
        getNextMoviePageInteractor.execute(GetMoviesArgs(currentPage.next()))
    }

    override fun refreshMovies() {
        currentPage = currentPage.copy(number = 0)
        loadMoreMovies()
    }

    override fun changeFilter(filterStr: String) {
        currentPage = MoviePage(filterStr = filterStr, number = 0)
        timer.cancel()
        timer = timerUtil.schedule(SEARCH_DELAY) {
            getNextMoviePageInteractor.execute(GetMoviesArgs(currentPage.next()))
        }
    }

    override fun selectMovie(movie: Movie) {
        mvpView?.showMessage(movie.title)
    }

    override fun addMovieToFavorites(movie: Movie) {
        addMovieToFavoritesInteractor.execute(movie)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        removeMovieFromFavoritesInteractor.execute(movie)
    }

    @Subscribe
    fun onGetMoviesAsyncStart(event: GetMoviesAsyncStartEvent) {
        removeStickyEvent(event)
        mvpView?.applyState(Loading(event.moviePage.isFirst, event.moviePage.isLast))
    }

    @Subscribe
    fun onGetMoviesSuccess(event: GetMoviesSuccessEvent) {
        removeStickyEvent(event)

        if (event.moviePage.filterStr == currentPage.filterStr) {
            currentPage = event.moviePage
            mvpView?.applyState(Data(currentPage.movies.toMutableList(), currentPage.isFirst, currentPage.isLast))
        }
    }

    @Subscribe
    fun onGetMoviesFailure(event: GetMoviesFailureEvent) {
        removeStickyEvent(event)

        if (event.moviePage.filterStr == currentPage.filterStr)
            mvpView?.applyState(Error(event.exception))
    }

    @Subscribe
    fun onAddMovieToFavoritesFinish(event: AddMovieToFavoritesFinishEvent) {
        removeStickyEvent(event)
        mvpView?.updateMovie(event.movie)
    }

    @Subscribe
    fun onRemoveMovieFromFavoritesFinish(event: RemoveMovieFromFavoritesFinishEvent) {
        removeStickyEvent(event)
        mvpView?.updateMovie(event.movie)
    }
}