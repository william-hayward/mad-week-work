package com.example.week_9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val poiListView = findViewById<RecyclerView>(R.id.poiListView)
        poiListView.layoutManager = LinearLayoutManager(this)

        poiListView.adapter = Adapter( listOf("Immersive Technologies",
            "Mobile Application Development",
            "Web Application Development"),
            listOf("Learn about developing augmented reality applications for the web, making use of three.js and A-Frame.",
            "Learn about Android development using Kotlin.",
            "Learn about web application development, including web APIs, JSON and AJAX.")



        )


    }
}


