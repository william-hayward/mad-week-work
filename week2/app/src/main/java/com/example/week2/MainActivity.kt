package com.example.week2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View.OnClickListener
import android.view.View
import android.widget.TextView
import android.widget.EditText
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)

    }

    override fun onClick(v: View?){

        val num1 = findViewById<EditText>(R.id.et1)
        val num2 = findViewById<EditText>(R.id.et2)

        val view = findViewById<TextView>(R.id.tv1)

        var total = 0.0

        when(v?.id) {
            R.id.btn1 -> {
                total = num1.text.toString().toDouble() + num2.text.toString().toDouble()
            }
            R.id.btn2 -> {
                total = num1.text.toString().toDouble() - num2.text.toString().toDouble()
            }
            R.id.btn3 -> {
                total = num1.text.toString().toDouble() * num2.text.toString().toDouble()
            }
            R.id.btn4 -> {
                total = num1.text.toString().toDouble() / num2.text.toString().toDouble()
            }
        }
        view.text = "Result: $total"
    }
}