package com.example.mycapstone.history.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1)
abstract class HistoryDb : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object{
        @Volatile
        private var INSTANCE: HistoryDb? = null

        fun getDatabase(context: Context): HistoryDb{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDb::class.java,
                    "history_database"
                ).build()
                INSTANCE =instance
                instance
            }
        }
    }
}