package com.example.week10

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Frag1(val db: SongsDatabase) : Fragment(R.layout.frag1) {
    override fun onViewCreated(fragmentView: View, b: Bundle?) {
        val btn1 = fragmentView.findViewById<Button>(R.id.btn1)
        val et1 = fragmentView.findViewById<EditText>(R.id.et1)
        val tv1 = fragmentView.findViewById<TextView>(R.id.tv1)

        btn1.setOnClickListener {
            lifecycleScope.launch {
                Log.d("Week 10", "Button works.")
                var song = listOf<Songs>()
                val artist = et1.text.toString()
                var str = ""
                withContext(Dispatchers.IO) {
                    song = db.songsDao().getSongsByArtist(artist)
                    Log.d("Week 10", "completed the query")
                }
                //str += song.size.toString()
                for(s in song) {
                    str += "Song: ${s.title}, Artist: ${s.artist}, Year: ${s.year}\n"
                    Log.d("Week 10", "this part works: $song")
                }
                tv1.text = str

                if(song.isEmpty()) {
                    str += "Error! Artist not found in database."
                    tv1.text = str
                }

                }
            }
        }
    }
