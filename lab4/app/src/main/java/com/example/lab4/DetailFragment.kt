package com.example.lab4


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.lab4.dummy.WeatherContent
import kotlinx.android.synthetic.main.fragment_detail.*
import java.net.URLDecoder
import kotlin.collections.ArrayList



class DetailFragment : Fragment() {
    private var items = ArrayList<String>()
    private val TAG = "DetailFragment"

    fun showDetailInfo(){
        items.clear()
        val position = arguments?.getInt(ARG_POSITION)
        if(position != null){
            val item = WeatherContent.ITEMS[position]
            items.add("Date: " + item.date)
            items.add("Time of day: " + item.tod)
            items.add("Temperature: " + item.temp)
            items.add("WInd: " + item.wind)
            items.add("Cloud: " + item.cloud)
            items.add("Humidity: " + item.humidity)
            items.add("Pressure: " + item.pressure)

            val list = detailList
            list.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, items)
        }
    }

    override fun onStart() {
        super.onStart()
        showDetailInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    companion object {

        const val ARG_POSITION = "position of element in RecycleView"

        @JvmStatic
        fun newInstance(position: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position)
                }
            }
    }
}
