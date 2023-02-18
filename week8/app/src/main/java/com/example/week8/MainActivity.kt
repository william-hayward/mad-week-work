package com.example.week8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.httpGet
// import com.github.kittinunf.fuel.json.responseJson // for JSON - uncomment when needed
// import com.github.kittinunf.fuel.gson.responseObject // for GSON - uncomment when needed
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        val artist = findViewById<EditText>(R.id.etArtist)
        val textView = findViewById<RecyclerView>(R.id.poiListView)
        val searchButton = findViewById<Button>(R.id.btn1)

        textView.layoutManager = LinearLayoutManager(this)

        val listSongName = mutableListOf<String>()
        val listDescription = mutableListOf<String>()
        textView.adapter = Adapter(listSongName, listDescription, { AlertDialog.Builder(this)
            .setPositiveButton("OK", null)
            .setMessage("Information: ${listDescription[it]}")
            .show()})
        textView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))

        searchButton.setOnClickListener {

            listSongName.clear()
            listDescription.clear()

            var Artist = artist.text.toString()

            val url = "https://wadweek-dtbnq.run-eu-central1.goorm.io/songs/artist/$Artist"
            url.httpGet().responseJson { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val jsonArray = result.get().array()
                        var str = ""
                        for (i in 0 until jsonArray.length()) {
                            val curObj = jsonArray.getJSONObject(i)
                            //str += "Artist: ${curObj.getString("artist")}\nSong: ${
                                //curObj.getString(
                                    //"title"
                                //)
                            //}\n\n"

                            listSongName.add("${curObj.getString("title")}")
                            listDescription.add("Artist: ${curObj.getString("artist")}\n" +
                                    "Release Year: ${curObj.getString("year")}")


                        }
                        //textView.text = str
                    }

                    is Result.Failure -> {
                        //textView.text = "ERROR ${result.error.message}"
                            listSongName.add("ERROR!")
                            listDescription.add("${result.error.message}")

                    }



                }
                textView.adapter?.notifyDataSetChanged()

            }
        }
    }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId) {
                R.id.create -> {
                    Toast.makeText(this, "Create item chosen", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,CreateActivity::class.java)
                    startActivity(intent)
                    return true
                }

            }
            return false
        }



}