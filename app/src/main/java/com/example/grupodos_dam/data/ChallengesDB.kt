package com.example.grupodos_dam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grupodos_dam.model.Challenge
import com.example.grupodos_dam.utils.Constants.NAME_BD

@Database(entities = [Challenge::class], version = 1)
abstract class ChallengesDB : RoomDatabase() {

    abstract fun challengesDao(): ChallengesDao

    companion object{
        fun getDatabase(context: Context): ChallengesDB {
            return Room.databaseBuilder(
                context.applicationContext,
                ChallengesDB::class.java,
                NAME_BD
            ).build()
        }
    }
}