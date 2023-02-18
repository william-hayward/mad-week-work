package com.example.week_11_2


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView



class mapFragment : Fragment(R.layout.map){
    var map1: MapView? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Configuration.getInstance()
            .load(activity, PreferenceManager.getDefaultSharedPreferences(activity));

        map1 = view.findViewById(R.id.map1)


    }

    override fun onStart() {
        super.onStart()
        setPosition(51.05, -0.72)
    }

    fun setPosition(lat: Double, lon: Double, zoom: Double = 14.0){
    map1?.controller?.setCenter(GeoPoint(lat, lon))
    map1?.controller?.setZoom(zoom)
    }

}