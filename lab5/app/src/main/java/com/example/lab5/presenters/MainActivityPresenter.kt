package com.example.lab5.presenters

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.lab5.dummy.WeatherContent
import com.example.lab5.helpers.ApiHelper
import com.example.lab5.views.IMainActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class MainActivityPresenter(private val context : Context): MvpPresenter<IMainActivity>() {

    private val TAG = "MainActivityPresenter"
    private val KEY = "WeatherInfo"

    fun showDetails(position: Int){
        viewState.showDetails(position)
    }

    fun saveWeatherInfo() {
        val prefEditor = PreferenceManager.getDefaultSharedPreferences(context)
        val jsonString: String = Gson().toJson(WeatherContent.ITEMS)
        prefEditor.edit().putString(KEY, jsonString).apply()
    }

    fun getWeatherInfo() {
        viewState.showLoadingStart()
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val jsonString = preferences.getString(KEY, null)

        if (jsonString == null) {
            Log.d(TAG, "weatherInfo is missing")
            viewState.showMessage("weatherInfo is missing")
            getData()
        } else {
            Log.d(TAG, "weatherInfo has been restored")
            viewState.showMessage("weatherInfo has been restored")
            WeatherContent.ITEMS =
                Gson().fromJson(jsonString, object : TypeToken<MutableList<WeatherContent.WeatherItem>>() {}.type)
            WeatherContent.processingData()
            viewState.updateList()
            viewState.showLoadingEnd()
        }
    }

    fun getData() {
        val apiHelper = ApiHelper.create()
        //val call = apiHelper.getWeatherInfo()
        val call = apiHelper.getWeatherInfo(24)
            .enqueue(object : Callback<List<WeatherContent.WeatherItem>> {
                override fun onFailure(call: Call<List<WeatherContent.WeatherItem>>, t: Throwable) {
                    viewState.showMessage(t.toString())
                    viewState.showLoadingEnd()
                }
                override fun onResponse(
                    call: Call<List<WeatherContent.WeatherItem>>,
                    response: Response<List<WeatherContent.WeatherItem>>
                ) {
                    if (response.code() == 200) {
                        val weatherResponse = response.body()!!
                        WeatherContent.ITEMS = weatherResponse.toMutableList()
                        WeatherContent.processingData()
                        viewState.updateList()
                        val str: String = Gson().toJson(WeatherContent.ITEMS)
                        Log.d(TAG, str)
                        saveWeatherInfo()
                        viewState.showLoadingEnd()
                    }
                }
            })
    }
}