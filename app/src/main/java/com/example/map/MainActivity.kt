package com.example.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main)

        val view = findViewById<TextView>(R.id.tv1)
        val viewTwo = findViewById<TextView>(R.id.tv2)

        val btn1 = findViewById<Button>(R.id.btn1)

        val map1 = findViewById<MapView>(R.id.map1)
        map1.controller.setZoom(14.0)
        map1.controller.setCenter(GeoPoint(51.05, -0.72))

        btn1.setOnClickListener(this)
    }

        override fun onClick(v: View?){
            val lat = findViewById<EditText>(R.id.et1)
            val long = findViewById<EditText>(R.id.et2)
            val map1 = findViewById<MapView>(R.id.map1)


            var latitude = 0.0
            var longitude = 0.0

            when(v?.id) {
                R.id.btn1 -> {
                    latitude = lat.text.toString().toDouble()
                    longitude = long.text.toString().toDouble()

                    map1.controller.setZoom(14.0)
                    map1.controller.setCenter(GeoPoint(latitude, longitude))
                }


        }








    }

}
