package com.example.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UlubioneDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUlubione(ulubiony: UlubioneEntity)

    @Update
    fun updateUlubione(ulubiony: UlubioneEntity)

    @Delete
    fun deleteUlubione(ulubiony: UlubioneEntity)

    @Query("SELECT * FROM UlubioneEntity")
    fun getUlubione(): List<UlubioneEntity>

    @Query("SELECT * FROM UlubioneEntity")
    fun livelubione(): LiveData<List<UlubioneEntity>>

    @Query("SELECT * FROM UlubioneEntity WHERE link=:id ")
    fun loadSingle(id: String): LiveData<UlubioneEntity>

    @Query("DELETE FROM UlubioneEntity WHERE link = :id")
    fun deleteByLink(id: String)
}