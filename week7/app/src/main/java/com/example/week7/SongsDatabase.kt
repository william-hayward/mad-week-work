package com.example.week7

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Songs::class), version = 1, exportSchema = false)
public abstract class SongsDatabase: RoomDatabase() {
    abstract fun songsDao(): SongsDao

    companion object {
        private var instance: SongsDatabase? = null

        fun getDatabase(ctx:Context) : SongsDatabase {
            var tmpInstance = instance
            if(tmpInstance == null) {
                tmpInstance = Room.databaseBuilder(
                    ctx.applicationContext,
                    SongsDatabase::class.java,
                    "songsDatabase"
                ).build()
                instance = tmpInstance
            }
            return tmpInstance
        }
    }
}