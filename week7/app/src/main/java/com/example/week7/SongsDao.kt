package com.example.week7

import androidx.room.*

@Dao
interface SongsDao {

    @Query("SELECT * FROM songs WHERE id=:id")
    fun getSongById(id: Long): Songs?

    @Insert
    fun insert(songs: Songs) : Long

    @Update
    fun update(songs: Songs) : Int

    @Delete
    fun delete(songs: Songs) : Int
}