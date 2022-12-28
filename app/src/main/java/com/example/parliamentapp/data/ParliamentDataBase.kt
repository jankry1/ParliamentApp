package com.example.parliamentapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: creating the database
 */

@Database(entities = [Parliament::class, Comment::class, Like::class, Dislike::class], version = 2, exportSchema = false)
abstract class ParliamentDataBase: RoomDatabase() {

    abstract fun parliamentDAO(): ParliamentDAO
    companion object {
        @Volatile
        private var INSTANCE: ParliamentDataBase? = null

        fun getDatabase(context: Context = App.appContext): ParliamentDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, ParliamentDataBase::class.java, "parliament_database").fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}