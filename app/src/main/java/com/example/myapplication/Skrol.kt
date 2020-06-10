package com.example.myapplication

import android.os.AsyncTask
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Skrol(private val exampleList: ArrayList<Produkt>, private val adaptor: TestAdapter, private val menager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {
    var ostatniaIlosc = 0
    var jestjuz = 1
    public var filtowanie = false
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if(!filtowanie) {
            if (menager.findLastVisibleItemPosition() > exampleList.size - 8 && exampleList.size != ostatniaIlosc) {
                jestjuz += 1
                ZaladujAsync(exampleList, adaptor, jestjuz).execute()
                ostatniaIlosc = exampleList.size

            }
        }
        super.onScrolled(recyclerView, dx, dy)
    }
    fun wylacz() {
        filtowanie = true
        ostatniaIlosc = 0
        jestjuz = 1
    }
    fun wlacz() {
        if (filtowanie) {
            filtowanie = false
            ostatniaIlosc = 0
            jestjuz = 1
        }
    }

}