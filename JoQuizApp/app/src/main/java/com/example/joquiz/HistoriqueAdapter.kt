package com.example.joquiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoriqueAdapter(private val context: Context, private val data: List<ScoreResult>) : RecyclerView.Adapter<HistoriqueAdapter.ItemViewHolder>() {

    //Holds a reference to the views inflated in onCreateViewHolder
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titreHistoriqueQuiz: TextView = view.findViewById(R.id.textview_TitreHistoriqueQuiz)
        val scoreHistoriqueQuiz: TextView = view.findViewById(R.id.textview_ScoreHistoriqueQuiz)
    }

    //Inflates the item row layout onto a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflatedView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_historique, parent, false)

        return ItemViewHolder(inflatedView)
    }

    //Returns the number of items in the data set
    override fun getItemCount(): Int {
        return data.size
    }

    //Updates the values on each row
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.titreHistoriqueQuiz.text = data[position].title
        holder.scoreHistoriqueQuiz.text = data[position].score.toString()
    }


}
