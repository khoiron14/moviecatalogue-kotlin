package com.khoiron14.moviecatalogue.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.khoiron14.moviecatalogue.R
import com.khoiron14.moviecatalogue.ui.favorite.movie.MovieFavoriteFragment
import com.khoiron14.moviecatalogue.ui.favorite.tvshow.TvShowFavoriteFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteFragment : Fragment() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewPager = view_pager as ViewPager
        setupViewPager(viewPager!!)

        tabLayout = tab_layout as TabLayout
        tabLayout?.setupWithViewPager(viewPager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = FavoritePagerAdapter(childFragmentManager)
        adapter.addFragment(MovieFavoriteFragment(), getString(R.string.movie))
        adapter.addFragment(TvShowFavoriteFragment(), getString(R.string.tv_show))
        viewPager.adapter = adapter
    }
}
