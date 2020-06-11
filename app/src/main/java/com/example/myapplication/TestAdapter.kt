package com.example.myapplication

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.room.UlubioneEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.podglad_produktu.view.*


class TestAdapter(private val exampleList: List<Produkt>, val context: Context?) :
    RecyclerView.Adapter<TestAdapter.ExampleViewHolder>() {
    var ulubioneLista = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.podglad_produktu,
            parent, false)
        return ExampleViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        if(ulubioneLista.contains(currentItem.link)) {
            holder.ulubione.setImageResource(R.drawable.ic_star_black_24dp)
        }
        else {
            holder.ulubione.setImageResource(R.drawable.ic_star_border_black_24dp)
        }
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
        holder.ceneo_cena.paintFlags =  holder.ceneo_cena.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        Picasso.get().load(currentItem.obrazsrc).into(holder.miniaturka)
        holder.ulubione.setOnClickListener {
            if(ulubioneLista.contains(currentItem.link)) {
                UlubioneUsun(currentItem.link, this.context!!).execute()
                Toast.makeText(context,
                    "Usunięto z ulubionych",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                UlubioneAkcja(currentItem.link, this.context!!).execute()
                Toast.makeText(context,
                    "Dodano do ulubionych",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
    override fun getItemCount() = exampleList.size
    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.text_view_1
        val textView2: TextView = itemView.text_view_2
        val miniaturka: ImageView = itemView.image_miniaturka
        val ulubione: ImageView = itemView.image_ulubiome
        val ceneo_cena: TextView = itemView.cena_ceneo
        val promocja: TextView = itemView.przecena
        val procent: TextView = itemView.procent
    }
    fun setUlubione(noweu: ArrayList<String>) {
        ulubioneLista = noweu
        this.notifyDataSetChanged()
    }
}