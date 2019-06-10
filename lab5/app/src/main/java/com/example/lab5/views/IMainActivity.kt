package com.example.lab5.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface IMainActivity: MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoadingStart()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoadingEnd()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateList()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showDetails(position: Int)
}