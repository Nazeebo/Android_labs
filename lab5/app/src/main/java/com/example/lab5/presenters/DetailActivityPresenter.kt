package com.example.lab5.presenters

import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable
import com.arellomobile.mvp.MvpPresenter
import com.example.lab5.views.IDetailActivity

class DetailActivityPresenter: MvpPresenter<IDetailActivity>() {
    fun checkOrientation(orientation: Int){
        if(orientation == Configuration.ORIENTATION_LANDSCAPE)
            viewState.shouldFinish()
    }

    fun mayAddFragment(position : Int){
        viewState.addFragment(position)
    }
}