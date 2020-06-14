package com.example.myapplication

import android.os.AsyncTask
import com.google.gson.Gson
import java.net.URL

public class WyszukajAsync(private val exampleList: ArrayList<Produkt>, private val adaptor: TestAdapter, private val nazwa: String): AsyncTask<Void, Void, Int>() {
    val gson: Gson = Gson()
    override fun doInBackground(vararg params: Void?): Int {
        val api = "http://192.168.1.147:5000/api/okazje/search/?nazwa="
        val obraz = "https://f01.esfr.pl/foto/2/41711255249/c5ce63bbe017fedbb085491eb2bfb7ec/samsung-qled-qe55q67rat,41711255249_7.jpg"
        val apiResponse = URL(api + nazwa)
        val stronka: Stronka = gson.fromJson(apiResponse.readText(), Stronka::class.java)
        for(o in stronka.okazje){
            exampleList.add(Produkt(o.nazwa, o.opis, o.miniaturka, 1000.00, o.cena.toDouble(), o.strona))
        }
        //exampleList.add(Produkt("Pyk", "Fyk"))

        return 0
    }

    override fun onPostExecute(result: Int?) {
        adaptor.notifyDataSetChanged()
        super.onPostExecute(result)
    }
}


