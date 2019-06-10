package com.example.lab5.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface IDetailActivity: MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun shouldFinish()

    @StateStrategyType(SkipStrategy::class)
    fun addFragment(position : Int)
}