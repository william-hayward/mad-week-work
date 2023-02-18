package com.example.week_11

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class PersonDetailsFragment : Fragment(R.layout.persondetails) {
    var tv: TextView? = null
    var pendingText = ""

    val descriptions = hashMapOf(
        "Tim Berners-Lee" to "Inventor of the World Wide Web.",
        "John Lennon" to "Singer and songwriter from the Beatles whose life was cut tragically short in 1980.",
        "Linus Torvalds" to "Original developer of Linux.",
        "Barack Obama" to "President of the US. (2008-16)"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv = view.findViewById(R.id.tvPersonDetails)
        if(pendingText != "") {
            tv?.setText(descriptions[pendingText] ?: "I do not know about $pendingText!")
            pendingText = ""
        }
    }


    fun setText(person: String) {
        if(tv != null ) {
            tv?.text = descriptions[person] ?: "I do not know about $person !"
        } else {
            pendingText = person
        }
    }
}