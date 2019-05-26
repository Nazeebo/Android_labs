package com.example.lab4

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.example.lab4.dummy.WeatherContent
import com.example.lab4.helpers.ApiHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.nio.charset.Charset

class MainActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener {
    private val TAG: String = "MainActivity"
    private val KEY: String = "WeatherInfo"

    override fun onListFragmentInteraction(position: Int) {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWeatherInfo()

        val fragment = ItemFragment()
        fragment.setList(WeatherContent.INFO.toList().sortedBy { it.first })
        supportFragmentManager.beginTransaction()
            .replace(R.id.listFragment,fragment)
            .commit()
    }

    fun getData(){
        val apiHelper = ApiHelper.create()
        val call = apiHelper.getWeatherInfo(24)
            .enqueue(object: Callback<List<WeatherContent.WeatherItem>> {
                override fun onFailure(call: Call<List<WeatherContent.WeatherItem>>, t: Throwable) {
                    Snackbar.make(View(applicationContext), t.toString(), Snackbar.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<List<WeatherContent.WeatherItem>>,
                    response: Response<List<WeatherContent.WeatherItem>>
                ) {
                    if(response.code() == 200){
                        val weatherResponse = response.body()!!

                        WeatherContent.ITEMS = weatherResponse.toMutableList()
                        val str:String = Gson().toJson(WeatherContent.ITEMS)
                        Log.d(TAG, str)
                        saveWeatherInfo()
                    }
                }

            }

        )
    }

    fun saveWeatherInfo(){
        val prefEditor = PreferenceManager.getDefaultSharedPreferences(this)
        val jsonString : String = Gson().toJson(WeatherContent.ITEMS)
        prefEditor.edit().putString(KEY, jsonString).apply()
    }

    fun getWeatherInfo(){
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val jsonString = preferences.getString(KEY, null)

        if (jsonString == null){
            Log.d(TAG, "weatherInfo is missing")
            getData()
        }
        else {
            Log.d(TAG, "weatherInfo has been restored")
            WeatherContent.ITEMS = Gson().fromJson(jsonString, object : TypeToken<MutableList<WeatherContent.WeatherItem>>(){}.type)
        }
        WeatherContent.processingData()
    }

}

