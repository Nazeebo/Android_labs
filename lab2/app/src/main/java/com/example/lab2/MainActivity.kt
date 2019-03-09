package com.example.lab2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.Exception

class MainActivity : AppCompatActivity() {

    private fun buttonPressed(button: String): View.OnClickListener = View.OnClickListener() { view ->
        if (firstNum.text.isEmpty() or secondNum.text.isEmpty())
            Snackbar.make(view, "Заполните оба числа", Snackbar.LENGTH_LONG).show()
        else {
            try{
                val first = firstNum.text.toString().toInt()
                val second = secondNum.text.toString().toInt()

                when (button) {
                    "+" -> edit.text = (first + second).toString()
                    "-" -> edit.text = (first - second).toString()
                    "*" -> edit.text = (first * second).toString()
                    "/" -> if (second != 0) edit.text = (first / second).toString()
                    else edit.text = "inf"
                }
            }
            catch(e: Exception){
                Snackbar.make(view, "Введены слишком большие числа", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b1.setOnClickListener(buttonPressed(b1.text.toString()))
        b2.setOnClickListener(buttonPressed(b2.text.toString()))
        b3.setOnClickListener(buttonPressed(b3.text.toString()))
        b4.setOnClickListener(buttonPressed(b4.text.toString()))
    }
}
