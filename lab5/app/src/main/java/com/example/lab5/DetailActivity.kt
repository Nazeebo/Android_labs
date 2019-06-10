package com.example.lab5

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.lab5.presenters.DetailActivityPresenter
import com.example.lab5.views.IDetailActivity

class DetailActivity : MvpAppCompatActivity(), IDetailActivity {

    @InjectPresenter
    lateinit var presenter : DetailActivityPresenter

    @ProvidePresenter
    fun provideDetailPresenter() = DetailActivityPresenter()

    override fun shouldFinish() {
        finish()
        return
    }

    override fun addFragment(position: Int) {
        val detailFragment = DetailFragment.newInstance(position)

        Log.d(TAG, position.toString())
        supportFragmentManager.beginTransaction()
            .replace(R.id.detailFragment, detailFragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG,"onCreate: activity been created")
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish()
            return
        }
        //presenter.checkOrientation(resources.configuration.orientation)


        setContentView(R.layout.activity_detail)
        if(savedInstanceState == null){
            Log.d(TAG,"onCreate: SaveInstance = null")

            val position = intent.getIntExtra(DetailFragment.ARG_POSITION, 0)
            //presenter.mayAddFragment(position)

            val detailFragment = DetailFragment.newInstance(position)

            Log.d(TAG, position.toString())
            supportFragmentManager.beginTransaction()
                .replace(R.id.detailFragment, detailFragment)
                .commit()
        }
    }
    private val TAG = "DetailActivity"
}
