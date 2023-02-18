package com.example.week_11_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputs = supportFragmentManager.findFragmentById(R.id.inputFragment) as inputFragment
        val map = supportFragmentManager.findFragmentById(R.id.mapFragment) as mapFragment



        inputs.callback = { lat, lon ->
            map.setPosition(lat, lon)
        }


    }
}