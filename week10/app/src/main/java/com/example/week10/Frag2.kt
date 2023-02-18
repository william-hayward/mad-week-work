package com.example.week10

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Frag2(val db: SongsDatabase): Fragment(R.layout.frag2) {
    override fun onViewCreated(fragmentView: View, b: Bundle?) {
        val btn1 = fragmentView.findViewById<Button>(R.id.btn1)
        val Title = fragmentView.findViewById<EditText>(R.id.et1)
        val Artist = fragmentView.findViewById<EditText>(R.id.et2)
        val Year = fragmentView.findViewById<EditText>(R.id.et3)

        fun showDialog(message : String) {
            activity?.apply{
                AlertDialog.Builder(this)
                    .setPositiveButton("OK", null)
                    .setMessage(message)
                    .show()}

        }


        btn1.setOnClickListener{
            lifecycleScope.launch {
            var title = Title.text.toString()
            var artist = Artist.text.toString()
            var year = Year.text.toString().toInt()
            var song = Songs(0,title,artist,year)

            var id = 0L

            withContext(Dispatchers.IO) {
                id = db.songsDao().insert(song)
            }
                showDialog("Song added to the database. ${id}")
            }

        }


    }
}