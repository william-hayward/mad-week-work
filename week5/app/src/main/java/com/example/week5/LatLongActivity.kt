package com.example.week5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.view.View
import android.widget.EditText
import androidx.core.os.bundleOf
import org.osmdroid.views.MapView

class LatLongActivity: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lat_long)

        val regular = findViewById<Button>(R.id.btn1)
        regular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val lat = findViewById<EditText>(R.id.et1)
        val long = findViewById<EditText>(R.id.et2)


        var latitude = 0.0
        var longitude = 0.0

        when (v?.id) {
            R.id.btn1 -> {
                latitude = lat.text.toString().toDouble()
                longitude = long.text.toString().toDouble()
                sendBackLatLong(latitude, longitude)
            }
        }
    }

    fun sendBackLatLong(lat: Double, lon: Double ) {
        val intent = Intent()
        val bundle = bundleOf("com.example.lat" to lat, "com.example.lon" to lon)
        intent.putExtras(bundle)
        setResult(RESULT_OK, intent)
        finish()
    }
}