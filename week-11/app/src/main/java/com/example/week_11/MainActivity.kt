package com.example.week_11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity: AppCompatActivity() {
    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView (R.layout.activity_main)

        // Find each fragment using their IDs
        val personEntryFrag = supportFragmentManager.findFragmentById(R.id.personEntryFrag) as PersonEntryFragment
        val personDetailsFrag = supportFragmentManager.findFragmentById(R.id.personDetailsFrag) as PersonDetailsFragment

        // Set the callback of personEntryFrag (which will run when the button
        // is clicked, see below) to a lambda which calls the setText() method of
        // personDetailsFrag. In this way, the person entered in personEntryFrag
        // will be transferred to personDetailsFrag, which will then look up the
        // details of that person.
        personEntryFrag.callback = {
            personDetailsFrag.setText(it)
        }
    }
}