package com.example.week_11_2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class inputFragment : Fragment(R.layout.inputs) {
    var callback: ((Double, Double) -> Unit)? = null

    // when the view has been created, add the event listener to the button, so
    // the callback is called when it's clicked
    override fun onViewCreated(v: View, b: Bundle?) {

        val btnGo = v.findViewById(R.id.btnGo) as Button
        val etLat = v.findViewById(R.id.etLat) as EditText
        val etLong = v.findViewById(R.id.etLong) as EditText

        btnGo.setOnClickListener {
            // Invoke the callback, passing it the text in the edit text.
            // We cannot just do
            // callback(etPersonName.text.toString())
            // as the callback is nullable, and will be null until it's set.

            val lat = etLat.text.toString().toDouble()
            val long = etLong.text.toString().toDouble()

            callback?.invoke(lat, long)
        }
    }
}
