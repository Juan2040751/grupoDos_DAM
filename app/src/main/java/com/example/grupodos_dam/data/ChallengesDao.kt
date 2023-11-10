package com.example.grupodos_dam.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.grupodos_dam.model.Challenge

@Dao
interface ChallengesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallenge(challenge: Challenge)
    @Query("SELECT * FROM Challenge")
    suspend fun getListChallenges(): MutableList<Challenge>
    @Delete
    suspend fun deleteChallenge(challenge: Challenge)
    @Update
    suspend fun updateChallenge(challenge: Challenge)

}