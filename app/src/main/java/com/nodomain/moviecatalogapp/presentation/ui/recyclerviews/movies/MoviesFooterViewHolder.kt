package com.nodomain.moviecatalogapp.presentation.ui.recyclerviews.movies


import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.nodomain.moviecatalogapp.R
import com.nodomain.moviecatalogapp.presentation.extensions.hideByScale
import com.nodomain.moviecatalogapp.presentation.extensions.inflate
import com.nodomain.moviecatalogapp.presentation.extensions.showByScale


class MoviesFooterViewHolder private constructor(private val footerView: View)
    : RecyclerView.ViewHolder(footerView) {

    companion object {

        fun create(parent: ViewGroup?): MoviesFooterViewHolder {
            val itemView = createItemView(parent)
            return MoviesFooterViewHolder(itemView)
        }

        private fun createItemView(parent: ViewGroup?) = parent.inflate(R.layout.footer_movies)
    }

    fun show() {
        footerView.showByScale()
    }

    fun hide(finalVisibility: Int, onAnimationEndAction: () -> Unit) {
        footerView.hideByScale(finalVisibility, onAnimationEndAction)
    }
}