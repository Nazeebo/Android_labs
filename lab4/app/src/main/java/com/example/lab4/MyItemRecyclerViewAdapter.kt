package com.example.lab4

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.example.lab4.ItemFragment.OnListFragmentInteractionListener
import com.example.lab4.dummy.WeatherContent
import com.example.lab4.dummy.WeatherContent.WeatherItem

import kotlinx.android.synthetic.main.fragment_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [WeatherItem] and makes a call to the
 * specified [OnListFragmentInteractionListener]. */

class MyItemRecyclerViewAdapter(
    private val mValues: List<WeatherItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as WeatherItem
            val position = mValues.indexOf(item)
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(position)
        }
    }

    //Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    //Replace the content of the views (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mDate.text = item.date
        val tod:String =  when(item.tod){
            "0" -> "ночь"
            "1" -> "утро"
            "2" -> "день"
            "3" -> "вечер"
            else -> item.tod
        }
        WeatherContent.ITEMS[position].tod = tod
        holder.mDayPart.text = tod
        holder.mTemp.text = item.temp

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mDate: TextView = mView.date
        val mDayPart: TextView = mView.dayPart
        val mTemp: TextView = mView.temperature

        override fun toString(): String {
            return super.toString() + " '" + mTemp.text + "'"
        }
    }
}
