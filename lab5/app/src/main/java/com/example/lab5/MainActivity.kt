package com.example.lab5

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.lab5.dummy.WeatherContent
import com.example.lab5.helpers.DataHelper
import com.example.lab5.views.IMainActivity
import com.example.lab5.presenters.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), ItemFragment.OnListFragmentInteractionListener, ItemFragment.OnRefreshListener, IMainActivity {

    @InjectPresenter
    lateinit var mainPresenter : MainActivityPresenter

    @ProvidePresenter
    fun provideMainPresenter() : MainActivityPresenter{
        return MainActivityPresenter(applicationContext)
    }

    private val TAG: String = "MainActivity"
    val fragment = ItemFragment()

    override fun onListFragmentInteraction(position: Int) {
        Log.d(TAG, position.toString())
        mainPresenter.showDetails(position)
    }

    override fun onRefresh() {
        mainPresenter.getData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.listFragment, fragment)
            .commit()
    }


    override fun onResume() {
        super.onResume()
        mainPresenter.getWeatherInfo()
    }

    override fun showLoadingStart() {
        fragment.swipeContainer.isRefreshing = true
    }

    override fun showLoadingEnd() {
        fragment.swipeContainer.isRefreshing = false
    }


    override fun showMessage(message : String) {
        Snackbar.make(main, message, Snackbar.LENGTH_LONG).show()
    }

    override fun updateList() {
        fragment.setList(WeatherContent.INFO.toList().sortedBy { it.first })
    }

    override fun showDetails(position: Int) {
        Log.d(TAG, position.toString())

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "landscape")
            val detailFragment = DetailFragment.newInstance(position)
            supportFragmentManager.beginTransaction()
                .replace(R.id.details, detailFragment)
                .commit()
        } else {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra(DetailFragment.ARG_POSITION, position)
            startActivity(intent)
        }
    }
}

