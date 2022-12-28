package com.example.parliamentapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: Dao class, where I define my database interactions
 */

@Dao
interface ParliamentDAO {

    // The implementation of the method will insert its parameters into the database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMember(parliament: Parliament)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addComment(comment: Comment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLike(like: Like)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDislike(dislike: Dislike)
    // This query is verified at compile time by Room to ensure that it compiles fine against the database.
    @Query("SELECT * FROM parliament_table ORDER BY personNumber")
    fun readAllData(): LiveData<List<Parliament>>

    @Query("SELECT * FROM comment")
    fun readAllComment(): LiveData<List<Comment>>

    @Query("SELECT * FROM like")
    fun readAllLike(): LiveData<List<Like>>

    @Query("SELECT * FROM dislike")
    fun readAllDislike(): LiveData<List<Dislike>>
}