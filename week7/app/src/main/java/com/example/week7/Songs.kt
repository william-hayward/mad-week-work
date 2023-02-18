package com.example.week7

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="songs")

data class Songs(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    var artist: String,
    var year: Int
)