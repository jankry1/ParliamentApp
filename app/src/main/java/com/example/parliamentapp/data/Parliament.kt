package com.example.parliamentapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: data classes
 */

@Entity(tableName = "parliament_table")
data class Parliament (
    @PrimaryKey
    val personNumber: Int,
    val seatNumber: Int,
    val last: String,
    val first: String,
    val party: String,
    val minister: Boolean,
    val picture: String,
    val twitter: String,
    val bornYear: Int,
    val constituency: String
)
@Entity(tableName = "like")
data class Like(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val like: Int,
    val personNumber: Int
)

@Entity(tableName = "comment")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val comment: String,
    val personNumber: Int
)
@Entity(tableName = "dislike")
data class Dislike(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dislike: Int,
    val personNumber: Int
)

