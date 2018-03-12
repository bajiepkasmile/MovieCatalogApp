package com.nodomain.moviecatalogapp.presentation.ui.fragments


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import com.nodomain.moviecatalogapp.R
import com.nodomain.moviecatalogapp.model.Movie
import com.nodomain.moviecatalogapp.presentation.mvp.moviecatalog.*
import com.nodomain.moviecatalogapp.presentation.extensions.*
import com.nodomain.moviecatalogapp.presentation.ui.recyclerviews.movies.MovieItemEventListener
import com.nodomain.moviecatalogapp.presentation.ui.recyclerviews.movies.MoviesAdapter
import com.nodomain.moviecatalogapp.presentation.utils.ReleaseDateFormatConverter
import kotlinx.android.synthetic.main.fragment_movie_catalog.*
import javax.inject.Inject


private const val INSTANCE_STATE_SRL_MOVIES_VISIBILITY = "srlMoviesVisibility"
private const val INSTANCE_STATE_PB_LOADING_MOVIES_VISIBILITY = "pbLoadingMoviesVisibility"
private const val INSTANCE_STATE_PB_REFRESHING_VISIBILITY = "pbRefreshingVisibility"
private const val INSTANCE_STATE_FAB_RETRY_VISIBILITY = "fabRetryVisibility"
private const val INSTANCE_STATE_TV_STATUS_VISIBILITY = "tvStatusVisibility"
private const val INSTANCE_STATE_TV_STATUS_STATE = "tvStatusState"
private const val TV_STATUS_STATE_ERROR = "tvStatusStateError"
private const val TV_STATUS_STATE_NO_RESULTS = "tvStatusStateNoResults"
private const val TV_STATUS_STATE_NOT_SET = "tvStatusStateNotSet"


class MovieCatalogFragment : MvpFragment<MovieCatalogMvpView, MovieCatalogMvpPresenter>(),
        MovieCatalogMvpView, MovieItemEventListener {

    companion object {

        fun newInstance() = MovieCatalogFragment()
    }

    @Inject
    lateinit var releaseDateFormatConverter: ReleaseDateFormatConverter

    private val snackbarNoNetwork by lazy {
        Snackbar.make(cl_content, R.string.no_network, Snackbar.LENGTH_LONG)
    }
    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(releaseDateFormatConverter, this, mutableListOf())
    }
    private val canLoadMoreMovies: Boolean
        get() = currentState is Data && !(currentState as Data).isLast

    private var currentState: MovieCatalogViewState = Init()
    private var currentFilter = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app.applicationComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?) = container.inflate(R.layout.fragment_movie_catalog)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureViews()
        setListeners()

        if (savedInstanceState == null)
            showStartAnimation(onAnimationEndAction = { mvpPresenter.loadMoreMovies() })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) return

        srl_movies.visibility = savedInstanceState.getInt(INSTANCE_STATE_SRL_MOVIES_VISIBILITY, View.GONE)
        pb_loading_movies.visibility =
                savedInstanceState.getInt(INSTANCE_STATE_PB_LOADING_MOVIES_VISIBILITY, View.GONE)
        pb_refreshing.visibility = savedInstanceState.getInt(INSTANCE_STATE_PB_REFRESHING_VISIBILITY, View.GONE)
        fab_retry.visibility = savedInstanceState.getInt(INSTANCE_STATE_FAB_RETRY_VISIBILITY, View.GONE)
        tv_status.visibility = savedInstanceState.getInt(INSTANCE_STATE_TV_STATUS_VISIBILITY, View.GONE)

        val tvStatusState = savedInstanceState.getString(INSTANCE_STATE_TV_STATUS_STATE, TV_STATUS_STATE_NOT_SET)
        if (tvStatusState == TV_STATUS_STATE_ERROR)
            setTvStatusToError()
        else if (tvStatusState == TV_STATUS_STATE_NO_RESULTS)
            setTvStatusToNoResults()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
                outState?.putInt(INSTANCE_STATE_SRL_MOVIES_VISIBILITY, srl_movies.visibility)
        outState?.putInt(INSTANCE_STATE_PB_LOADING_MOVIES_VISIBILITY, pb_loading_movies.visibility)
        outState?.putInt(INSTANCE_STATE_PB_REFRESHING_VISIBILITY, pb_refreshing.visibility)
        outState?.putInt(INSTANCE_STATE_FAB_RETRY_VISIBILITY, fab_retry.visibility)
        outState?.putInt(INSTANCE_STATE_TV_STATUS_VISIBILITY, tv_status.visibility)

        if (currentState is Error)
            outState?.putString(INSTANCE_STATE_TV_STATUS_STATE, TV_STATUS_STATE_ERROR)
        else if (currentState is Data && moviesAdapter.isEmpty)
            outState?.putString(INSTANCE_STATE_TV_STATUS_STATE, TV_STATUS_STATE_NO_RESULTS)
        else
            outState?.putString(INSTANCE_STATE_TV_STATUS_STATE, TV_STATUS_STATE_NOT_SET)

        super.onSaveInstanceState(outState)
    }

    override fun applyState(newState: MovieCatalogViewState) {
        when (currentState) {
            is Init -> when(newState) {
                is Loading -> changeState(currentState as Init, newState)
                is Error -> changeState(currentState as Init, newState)
            }
            is Data -> when(newState) {
                is Loading -> changeState(currentState as Data, newState)
                is Error -> changeState(currentState as Data, newState)
            }
            is Loading -> when(newState) {
                is Data -> changeState(currentState as Loading, newState)
                is Loading -> changeState(currentState as Loading, newState)
                is Error -> changeState(currentState as Loading, newState)
            }
            is Error -> when(newState) {
                is Loading -> changeState(currentState as Error, newState)
                is Error -> changeState(currentState as Error, newState)
            }
        }
        currentState = newState
    }

    override fun updateMovie(movie: Movie) {
        moviesAdapter.updateMovie(movie)
    }

    override fun showMessage(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onMovieClick(movie: Movie) {
        mvpPresenter.selectMovie(movie)
    }

    override fun onLikeMovie(movie: Movie) {
        mvpPresenter.addMovieToFavorites(movie)
    }

    override fun onDislikeMovie(movie: Movie) {
        mvpPresenter.removeMovieFromFavorites(movie)
    }

    private fun onFilterChanged(filterStr: String) {
        currentFilter = filterStr
        mvpPresenter.changeFilter(filterStr)
    }

    private fun onSearchClick() {
        sv_filter.clearFocus()
        mvpPresenter.loadMoreMovies()
    }

    private fun onRefresh() {
        srl_movies.isRefreshing = false
        mvpPresenter.refreshMovies()
    }

    private fun onRetry() {
        mvpPresenter.loadMoreMovies()
    }

    private fun onLastItemReached() {
        if (canLoadMoreMovies)
            mvpPresenter.loadMoreMovies()
    }

    private fun configureViews() {
        val columnsCount = context.resources.getInteger(R.integer.recycler_view_column_count)
        rv_movies.layoutManager = GridLayoutManager(context, columnsCount)

        rv_movies.adapter = moviesAdapter

        srl_movies.setColorSchemeResources(R.color.blue)
    }

    private fun setListeners() {
        sv_filter.setOnQueryTextActions(
                submitAction = { onSearchClick() },
                changeAction = { query -> if (currentFilter != query) onFilterChanged(query) })
        srl_movies.setOnRefreshListener { onRefresh() }
        fab_retry.setOnClickListener({ onRetry() })
        fab_retry.setDisposableOnGlobalLayoutAction { fab_retry.visibility = View.GONE }
        rv_movies.setOnLastItemReachAction({ onLastItemReached() })
        snackbarNoNetwork.setOnDismissAction { fab_retry.show() }
    }

    private fun showStartAnimation(onAnimationEndAction: () -> Unit) {
        toolbar.setDisposableOnGlobalLayoutAction {
            toolbar.translationY = -toolbar.height.toFloat()
            toolbar.animate()
                    .translationY(0f)
                    .setInterpolator(DecelerateInterpolator())
                    .setDisposableOnAnimationEndAction(onAnimationEndAction)
                    .setDuration(250).start()
        }
    }

    private fun setTvStatusToError() {
        tv_status.setDrawableTop(R.drawable.ic_alert_big)
        tv_status.setText(R.string.could_not_process_request)
    }

    private fun setTvStatusToNoResults() {
        tv_status.setDrawableTop(R.drawable.ic_search_big)
        val noResultsText = getString(R.string.no_results, currentFilter)
        tv_status.text = noResultsText
    }

    private fun changeState(fromState: Init, toState: Loading) = toLoading(toState)

    private fun changeState(fromState: Init, toState: Error) = toError(toState)

    private fun changeState(fromState: Data, toState: Loading) = fromData(fromState, { toLoading(toState) })

    private fun changeState(fromState: Data, toState: Error) = fromData(fromState, { toError(toState) })

    private fun changeState(fromState: Loading, toState: Data) = fromLoading(fromState, { toData(toState) })

    private fun changeState(fromState: Loading, toState: Loading) = fromLoading(fromState, { toLoading(toState) })

    private fun changeState(fromState: Loading, toState: Error) = fromLoading(fromState, { toError(toState) })

    private fun changeState(fromState: Error, toState: Loading) = fromError(fromState, { toLoading(toState) })

    private fun changeState(fromState: Error, toState: Error) = fromError(fromState, { toError(toState) })

    private fun toData(state: Data) {
        if (state.isFirst)
            srl_movies.hideByTranslationAndAlpha(onAnimationEndAction = {
                moviesAdapter.setMovies(state.movies)
                if (moviesAdapter.isEmpty) {
                    setTvStatusToNoResults()
                    tv_status.showByScale()
                } else
                    srl_movies.showByTranslationAndAlpha()
            })
        else
            moviesAdapter.addMovies(state.movies)
    }

    private fun fromData(state:Data, onAnimationEndAction: () -> Unit) {
        if (moviesAdapter.isEmpty)
            tv_status.hideByScale(onAnimationEndAction = onAnimationEndAction)
        else
            onAnimationEndAction()
    }

    private fun toLoading(state: Loading) {
        when {
            moviesAdapter.isEmpty -> pb_loading_movies.showByScale()
            state.isFirst -> pb_refreshing.showByAlpha()
            else -> moviesAdapter.showFooter()
        }
    }

    private fun fromLoading(state: Loading, onAnimationEndAction: () -> Unit) {
        when {
            moviesAdapter.isEmpty -> pb_loading_movies.hideByScale(onAnimationEndAction = onAnimationEndAction)
            state.isFirst -> pb_refreshing.hideByAlpha(onAnimationEndAction = onAnimationEndAction)
            state.isLast -> moviesAdapter.hideFooter(View.GONE, onAnimationEndAction = onAnimationEndAction)
            else -> moviesAdapter.hideFooter(View.INVISIBLE, onAnimationEndAction = onAnimationEndAction)
        }
    }

    private fun toError(state: Error) {
        if (moviesAdapter.isEmpty) {
            setTvStatusToError()
            tv_status.showByScale()
            fab_retry.show()
        } else
            snackbarNoNetwork.show()
    }

    private fun fromError(state: Error, onAnimationEndAction: () -> Unit) {
        fab_retry.hide(onAnimationEndAction)
        if (moviesAdapter.isEmpty)
            tv_status.hideByScale()
    }
}