package com.nodomain.moviecatalogapp.presentation.ui.activities


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.nodomain.moviecatalogapp.presentation.ui.fragments.MovieCatalogFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null)
            addFragment(MovieCatalogFragment.newInstance())
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, fragment)
                .commit()
    }
}