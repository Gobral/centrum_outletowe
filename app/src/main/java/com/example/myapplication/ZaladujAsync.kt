package com.example.myapplication

import android.os.AsyncTask

public class ZaladujAsync(private val exampleList: ArrayList<Produkt>, private val adaptor: TestAdapter): AsyncTask<Void, Void, Int>() {
    override fun doInBackground(vararg params: Void?): Int {
        println("ładuję nowe")
        exampleList.add(Produkt("Pyk", "Fyk"))
        exampleList.add(Produkt("Pyk", "Fyk"))
        exampleList.add(Produkt("Pyk", "Fyk"))
        exampleList.add(Produkt("Pyk", "Fyk"))
        exampleList.add(Produkt("Pyk", "Fyk"))
        exampleList.add(Produkt("Pyk", "Fyk"))
        exampleList.add(Produkt("Pyk", "Fyk"))
        exampleList.add(Produkt("Pyk", "Fyk"))
        exampleList.add(Produkt("Pyk", "Fyk"))
        exampleList.add(Produkt("Pyk", "Fyk"))
        exampleList.add(Produkt("Pyk", "Fyk"))

        return 0
    }

    override fun onPostExecute(result: Int?) {
        adaptor.notifyDataSetChanged()
        super.onPostExecute(result)
    }
}