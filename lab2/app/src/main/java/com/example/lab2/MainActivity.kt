package com.example.lab2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private fun buttonPressed( button : Button ): View.OnClickListener = View.OnClickListener() { view ->
        if(firstNum.text.isEmpty() or  secondNum.text.isEmpty())
            Snackbar.make(view, "Заполните оба числа", Snackbar.LENGTH_LONG).show()
        else {
            val first = firstNum.text.toString().toInt()
            val second = secondNum.text.toString().toInt()
            when(button.text.toString()){
                "+" ->  text.text = (first + second).toString()
                "-" ->  text.text = (first - second).toString()
                "*" ->  text.text = (first * second).toString()
                "/" ->  if(second != 0) text.text = (first / second).toString()
                    else text.text = "inf"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b1.setOnClickListener(buttonPressed(b1))
        b2.setOnClickListener(buttonPressed(b2))
        b3.setOnClickListener(buttonPressed(b3))
        b4.setOnClickListener(buttonPressed(b4))
    }
}
