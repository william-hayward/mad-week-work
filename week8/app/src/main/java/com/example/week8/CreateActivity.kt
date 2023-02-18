package com.example.week8

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class CreateActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)


        val createButton = findViewById<Button>(R.id.btnCreate)

        createButton.setOnClickListener{

            val artisttext = findViewById<EditText>(R.id.etArtist)
            val songtext = findViewById<EditText>(R.id.etSong)
            val yeartext = findViewById<EditText>(R.id.etYear)

            val artist = artisttext.text.toString()
            val song = songtext.text.toString()
            val year = yeartext.text.toString().toInt()

            val url = "https://wadweek-dtbnq.run-eu-central1.goorm.io/songs/create"
            val postData = listOf("title" to song, "artist" to artist, "year" to year)
            url.httpPost(postData).response { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        Toast.makeText(this@CreateActivity, result.get().decodeToString(), Toast.LENGTH_LONG).show()
                    }

                    is Result.Failure -> {
                        Toast.makeText(this@CreateActivity, result.error.message, Toast.LENGTH_LONG).show()

                    }
                }
            }


        }

    }

}