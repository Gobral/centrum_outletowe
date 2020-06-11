package com.example.myapplication

import android.content.Context
import android.os.AsyncTask
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.UlubioneEntity
import java.lang.Exception
import java.util.*

public class UlubioneAkcja(private val link: String, private val contextAda: Context): AsyncTask<Void, Void, Int>() {

    override fun doInBackground(vararg params: Void?): Int {

        val noweUlubione = UlubioneEntity(link, Calendar.getInstance().time)
        try {
            AppDatabase.getAppDataBase(contextAda)!!.ulubioneDao().insertUlubione(noweUlubione)
        }
        catch (e: Exception){
            return 1
        }
        return 0
    }
}