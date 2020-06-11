package com.example.myapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class UlubioneEntity(
    @PrimaryKey val link: String,
    val dataDodania: Date
)