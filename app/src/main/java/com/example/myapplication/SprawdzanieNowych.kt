package com.example.myapplication

import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException


class SprawdzanieNowych(aktywnosc: AppCompatActivity) : AsyncTask<Void, Void, Int>() {
    private val aktywnoscReference = aktywnosc
    private val exapi = "http://192.168.1.147:5000/api/okazje"
    private val client = OkHttpClient();
    val JSON = "application/json; charset=utf-8".toMediaType()
    override fun doInBackground(vararg params: Void?): Int? {
        println("Start skanowania")

       // while (!verifyAvailableNetwork(aktywnoscReference)) {
        //    Thread.sleep(3000)
        //}
        val doctemp = Jsoup.connect("https://www.euro.com.pl/search/stan-outlet-doskonaly:outlet-dobry,d10,strona-1.bhtml?keyword=outlet&searchType=tag").maxBodySize(0).get().body()
        val ostatnia = doctemp.getElementsByClass("paging-number").last().text().toInt() + 1
        for(i in 1 until ostatnia) {
            val lista = Elements()
            val doc = Jsoup.connect("https://www.euro.com.pl/search/stan-outlet-doskonaly:outlet-dobry,d10,strona-$i.bhtml?keyword=outlet&searchType=tag").maxBodySize(0).get().body()
            lista.addAll(doc.select("a[class=js-save-keyword]"))
            //<h2 class="product-name">
            //lista.addAll(doc.select("h2[class=product-name]"))
            //lista.addAll(doc.getElementsByClass("product-name"))
            println(i.toString() + " " + lista.size);
            var czy = true
            for (c in lista) {
                if(czy) {
                    dodaj(c.text(), "https://www.euro.com.pl" + c.attr("href"))
                    //println(c.text())
                    //println(c.attr("href"))
                }
                czy = !czy
            }
        }
        return 0
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        // ...
    }
    fun dodaj(nazwa: String, link: String) {
        //println("{\"strona\": \"$link\", \"nazwa\": \"$nazwa\" }")
        val body: RequestBody = ("{\"strona\": \"$link\", \"nazwa\": \"$nazwa\" }").toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(exapi)
            .post(body)
            .build()

        try {
            val response: Response = client.newCall(request).execute()
            response.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}