package com.example.myapplication

import android.os.AsyncTask
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

public class ZaladujUlubione(private val exampleList: ArrayList<Produkt>, private val adaptor: TestAdapter, private val ulubione: ArrayList<String>): AsyncTask<Void, Void, Int>() {
    val gson: Gson = Gson()
    private val client = OkHttpClient();
    val JSON = "application/json; charset=utf-8".toMediaType()
    override fun doInBackground(vararg params: Void?): Int {
        val templist = ArrayList<Produkt>()
        val api = "http://192.168.1.147:5000/api/okazje/fav/"

        val obraz = "https://f01.esfr.pl/foto/2/41711255249/c5ce63bbe017fedbb085491eb2bfb7ec/samsung-qled-qe55q67rat,41711255249_7.jpg"

        //val body: RequestBody = ("{\"strona\": \"$link\", \"nazwa\": \"$nazwa\" }").toRequestBody(JSON)
        var bodyStr: String = ("{\"linki\": [ ")
        for(u in ulubione){
            bodyStr += " {\"url\": \"$u\"},"
        }

        bodyStr = bodyStr.substring(0, bodyStr.length - 1)
        bodyStr += "] }"

        val body: RequestBody = bodyStr.toRequestBody(JSON)

        val request: Request = Request.Builder()
            .url(api)
            .post(body)
            .build()

        try {
            val response: Response = client.newCall(request).execute()
            val wysloano : WysloanoC = gson.fromJson(response.body!!.string(), WysloanoC::class.java)
            for(o in wysloano.wyslano) {
                templist.add(Produkt(o.nazwa, o.strona, obraz, 1000.0, 30.0, o.strona))
            }
            if(templist.size > exampleList.size){
                exampleList.addAll(templist)
            }
            else {
                val arli1 = ArrayList<String>()
                val arli2 = ArrayList<String>()
                var dousuniecia = ""
                for (o in exampleList) {
                    arli1.add(o.link)
                }
                for (o in templist) {
                    arli2.add(o.link)
                }
                for (e in arli1) {
                    if (!arli2.contains(e)) {
                        println(e)
                        val numberIterator = exampleList.iterator()
                        while (numberIterator.hasNext()) {
                            val produkt = numberIterator.next()
                            if (produkt.link == e) {
                                numberIterator.remove()
                            }
                        }
                    }
                }
            }

            response.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        //exampleList.add(Produkt("Pyk", "Fyk"))

        return 0
    }

    override fun onPostExecute(result: Int?) {
        adaptor.notifyDataSetChanged()
        super.onPostExecute(result)
    }
}

class WysloanoC( public var wyslano: List<Okazja>) {

}