package com.example.week_11

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

// in PersonEntryFragment
class PersonEntryFragment : Fragment(R.layout.personentry) {
    var callback: ((String) -> Unit)? = null

    // when the view has been created, add the event listener to the button, so
    // the callback is called when it's clicked
    override fun onViewCreated(v: View, b: Bundle?) {

        val btnGo = v.findViewById(R.id.btnGo) as Button
        val etPersonName = v.findViewById(R.id.etPersonName) as EditText

        btnGo.setOnClickListener {
            // Invoke the callback, passing it the text in the edit text.
            // We cannot just do
            // callback(etPersonName.text.toString())
            // as the callback is nullable, and will be null until it's set.

            callback?.invoke(etPersonName.text.toString())
        }
    }
}