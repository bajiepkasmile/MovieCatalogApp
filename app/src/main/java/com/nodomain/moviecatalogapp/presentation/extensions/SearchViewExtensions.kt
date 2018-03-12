package com.nodomain.moviecatalogapp.presentation.extensions


import android.support.v7.widget.SearchView


fun SearchView.setOnQueryTextActions(submitAction: () -> Unit, changeAction: (query: String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(p0: String?): Boolean {
            submitAction()
            return true
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            changeAction(p0 ?: "")
            return true
        }
    })
}