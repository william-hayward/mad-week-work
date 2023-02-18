package com.example.week5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.view.MenuInflater
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import org.osmdroid.tileprovider.tilesource.TileSourceFactory


class MainActivity : AppCompatActivity() {

    private lateinit var mv:MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = findViewById<MapView>(R.id.map1)
        mv.controller.setZoom(14.0)
        mv.controller.setCenter(GeoPoint(51.05, -0.72))

        showDialog("onCreate called")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.chooseMap -> {
                Toast.makeText(this, "Map selection item chosen", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MapChooseActivity::class.java)
                mapChooseLauncher.launch(intent)
                return true
            }
            R.id.setLangLong -> {
                Toast.makeText(this, "Set Lat Long values item chosen", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,LatLongActivity::class.java)
                latLongLauncher.launch(intent)
                return true
            }
            R.id.preferences -> {
                Toast.makeText(this, "Preferences item chosen", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MyPrefsActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        showDialog("onResume called")

        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val lat = prefs.getString("lat", "50.721680")?.toDouble() ?: 50.721680
        val lon = prefs.getString("lon", "-1.878530")?.toDouble() ?: -1.878530
        val zoom = prefs.getString("zoom", "14")?.toDouble() ?: 14.0
        val style = prefs.getString("style", "Reg") ?: "Reg"


        mv.controller.setCenter(GeoPoint(lat, lon))
        mv.controller.setZoom(zoom)
        if (style == "Reg") {
            mv.setTileSource(TileSourceFactory.MAPNIK)
        } else {
            mv.setTileSource(TileSourceFactory.OpenTopo)
        }


    }

    override fun onStart() {
        super.onStart()
        showDialog("onStart called")
    }

    fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setPositiveButton("OK", null)
            .setMessage(message)
            .show()
    }



    val mapChooseLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // The lambda function starts here
            // Check that we get an OK (success) result from the second activity
            if (it.resultCode == RESULT_OK) {
                it.data?.apply {
                    val opentopomap = this.getBooleanExtra("com.example.opentopomap", false) // false is a default value
                    mv.setTileSource( if (opentopomap) TileSourceFactory.OpenTopo else TileSourceFactory.MAPNIK )
                }
            }
            // The lambda function ends here
        }

    val latLongLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                it.data?.apply {
                    val lat = this.getDoubleExtra("com.example.lat", 0.0)
                    val lon = this.getDoubleExtra("com.example.lon", 0.0)
                    mv.controller.setCenter(GeoPoint(lat, lon))
                }
            }
        }

}