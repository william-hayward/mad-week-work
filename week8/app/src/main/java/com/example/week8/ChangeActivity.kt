package com.example.week8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.view.View
import androidx.core.os.bundleOf

class ChangeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        val search = findViewById<Button>(R.id.btnSearchMenu)
        search.setOnClickListener {

        }
        val create = findViewById<Button>(R.id.btnCreateMenu)
        create.setOnClickListener {

        }

    }

}