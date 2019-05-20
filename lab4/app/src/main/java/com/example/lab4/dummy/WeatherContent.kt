package com.example.lab4.dummy

import java.util.ArrayList

object WeatherContent {

    var ITEMS: MutableList<WeatherItem> = ArrayList()

    //private val ITEM_MAP: MutableMap<String, WeatherItem> = HashMap()


    /*init {
        // Add some sample items.
        addItem(createWeatherItem("25.12.14","day","+25", "strong", "\u041c\u0430\u043b\u043e\u043e\u0431\u043b\u0430\u0447\u043d\u043e", "14", "750"))
        addItem(createWeatherItem("25.10.14","day","+22", "medium", "\u041f\u0430\u0441\u043c\u0443\u0440\u043d\u043e", "50", "760"))
        addItem(createWeatherItem("15.12.14","night","+5", "weak", "\u042f\u0441\u043d\u043e", "20", "755"))
    }*/

    private fun addItem(item: WeatherItem) {
        ITEMS.add(item)
    }

    private fun createWeatherItem(date: String, tod: String, temp : String, wind: String, cloud: String, humidity: String, pressure: String): WeatherItem {
        return WeatherItem(date, tod, temp, wind, cloud, humidity, pressure)
    }

    data class WeatherItem(val date: String, var tod: String, val temp: String, val wind: String, val cloud: String, val humidity: String, val pressure: String)
}
