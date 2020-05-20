package com.example.myapplication

import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class SprawdzanieNowych(aktywnosc: AppCompatActivity) : AsyncTask<Void, Void, Int>() {
    private val aktywnoscReference = aktywnosc
    override fun doInBackground(vararg params: Void?): Int? {
        println("Start skanowania")
       // while (!verifyAvailableNetwork(aktywnoscReference)) {
        //    Thread.sleep(3000)
        //}
        for(i in 1 until 180) {
            val lista = Elements()
            val doc = Jsoup.connect("https://www.euro.com.pl/search/stan-outlet-doskonaly:outlet-dobry,d10,strona-$i.bhtml?keyword=outlet&searchType=tag").get()
            lista.addAll(doc.select("a[class=js-save-keyword]"))
            println(i)
            for (c in lista) {
               // println(c.text())
                //println(c.attr("href"))
            }
        }
        return 0
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        // ...
    }
    private fun verifyAvailableNetwork(activity: AppCompatActivity):Boolean{
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetwork
        return  networkInfo!=null
    }
}