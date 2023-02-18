package com.example.week7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = SongsDatabase.getDatabase(application)

        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        val etSongId = findViewById<EditText>(R.id.etSongId)
        val etTitle = findViewById<EditText>(R.id.etSongTitle)
        val etArtist = findViewById<EditText>(R.id.etSongArtist)
        val etYear = findViewById<EditText>(R.id.etSongYear)

        val tvID = findViewById<TextView>(R.id.tv1)
        val tvTitle = findViewById<TextView>(R.id.tv2)
        val tvArtist = findViewById<TextView>(R.id.tv3)
        val tvYear = findViewById<TextView>(R.id.tv4)

        fun showDialog(message: String) {
            AlertDialog.Builder(this)
                .setPositiveButton("OK", null)
                .setMessage(message)
                .show()
        }



        btnSearch.setOnClickListener {
            lifecycleScope.launch {
                var song: Songs? = null
                val id = etSongId.text.toString().toLong()
                withContext(Dispatchers.IO) {
                    song = db.songsDao().getSongById(id)
                }
                song?.apply {
                    etTitle.setText(title)
                    etArtist.setText(artist)
                    etYear.setText("$year")
                }

                 if(song == null) {
                     showDialog("Song not found in database.")
                 }

            }
        }

        btnAdd.setOnClickListener{
            lifecycleScope.launch {

                var title = etTitle.text.toString()
                var artist = etArtist.text.toString()
                var year = etYear.text.toString().toInt()
                var song = Songs(0,title,artist,year)

                var id = 0L

                withContext(Dispatchers.IO) {
                    id = db.songsDao().insert(song)
                }
                etSongId.setText("$id")

                showDialog("Song added to the database.")


            }
        }

        btnUpdate.setOnClickListener {
            lifecycleScope.launch {
                var id = etSongId.text.toString().toLong()
                var name = etArtist.text.toString()
                var title = etTitle.text.toString()
                var year = etYear.text.toString().toInt()
                var song = Songs(id,title,name,year)
                withContext(Dispatchers.IO) {
                    db.songsDao().update(song)
                }
                showDialog("Song was updated in the database") // call our generic dialog function

            }
        }

        btnDelete.setOnClickListener {
            lifecycleScope.launch {
                var id = etSongId.text.toString().toLong()
                var name = etArtist.text.toString()
                var title = etTitle.text.toString()
                var year = etYear.text.toString().toInt()
                var song = Songs(id,title,name,year)
                withContext(Dispatchers.IO) {
                    db.songsDao().delete(song)
                }
                showDialog("Song was deleted from the database") // call our generic dialog function
            }
        }




    }
}