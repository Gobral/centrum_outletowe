package com.example.myapplication

import android.os.AsyncTask
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Skrol(private val exampleList: ArrayList<Produkt>, private val adaptor: TestAdapter, private val menager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {
    var ostatniaIlosc = 0
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (menager.findLastVisibleItemPosition() > exampleList.size - 8 && exampleList.size != ostatniaIlosc) {
            ZaladujAsync(exampleList, adaptor).execute()
            ostatniaIlosc = exampleList.size

        }
        super.onScrolled(recyclerView, dx, dy)
    }

}