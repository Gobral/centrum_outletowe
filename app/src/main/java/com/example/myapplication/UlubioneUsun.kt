package com.example.myapplication

import android.content.Context
import android.os.AsyncTask
import com.example.myapplication.room.AppDatabase

public class UlubioneUsun(private val link: String, private val contextAda: Context): AsyncTask<Void, Void, Int>() {

    override fun doInBackground(vararg params: Void?): Int {
        try {
            AppDatabase.getAppDataBase(contextAda)!!.ulubioneDao().deleteByLink(link)
        }
        catch (e: Exception){
            return 1
        }
        return 0
    }
}