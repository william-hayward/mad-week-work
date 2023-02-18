package com.example.week_9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// The names and descriptions to be displayed are passed as parameters to
// our Adapter.
class Adapter(val titles: List<String>,
                val descriptions: List<String>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // A ViewHolder is associated with a particular adapter, so it makes
    // sense to create the holder as an inner class of the adapter.
    // The ViewHolder takes the associated View as a parameter

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Inflate the child elements of the View and store them as properties,
        // so we have easy access to them later

        val tvName = itemView.findViewById(R.id.poiName) as TextView
        val tvDescription = itemView.findViewById(R.id.poiDescription) as TextView
    }

    // onCreateViewHolder() - called when a ViewHolder is first created.

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int) : RecyclerView.ViewHolder {
        // Inflate the XML layout and create a ViewHolder using it



        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedLayout = layoutInflater.inflate(R.layout.list, parent, false)
        return MyViewHolder(inflatedLayout)
    }

    // onBindViewHolder() - called when a ViewHolder is bound to a certain item of data.
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, index: Int) {
        // Cast to our specific ViewHolder class

        val myViewHolder = holder as MyViewHolder

        // Fill the name and description within the ViewHolder to the
        // current elements within our two lists of data

        myViewHolder.tvName.text = titles[index]
        myViewHolder.tvDescription.text = descriptions[index]
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}