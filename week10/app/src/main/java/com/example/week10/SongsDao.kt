package com.example.week10

import androidx.room.*

@Dao
interface SongsDao {

    @Query("SELECT * FROM songs WHERE id=:id")
    fun getSongById(id: Long): Songs?

    @Query("SELECT * FROM songs WHERE artist=:artist")
    fun getSongsByArtist(artist: String): List<Songs>

    @Insert
    fun insert(songs: Songs) : Long

    @Update
    fun update(songs: Songs) : Int

    @Delete
    fun delete(songs: Songs) : Int
}