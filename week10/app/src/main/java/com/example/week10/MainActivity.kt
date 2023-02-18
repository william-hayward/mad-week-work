package com.example.week10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = SongsDatabase.getDatabase(application)

        var nv = findViewById<NavigationView>(R.id.nv)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        val frag1 = Frag1(db)
        val frag2 = Frag2(db)


        nv.setNavigationItemSelectedListener {
            val frag = if (it.itemId == R.id.search) frag1 else frag2
            drawerLayout.closeDrawers()
            supportFragmentManager.commit {
                replace(R.id.frameLayout1, frag)
            }
            true
        }
        supportFragmentManager.commit{
            replace(R.id.frameLayout1, frag1)
        }
    }
}