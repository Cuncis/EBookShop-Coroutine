package com.reksa.ebookshopcoroutine.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reksa.ebookshopcoroutine.data.dao.BookDao
import com.reksa.ebookshopcoroutine.data.dao.CategoryDao
import com.reksa.ebookshopcoroutine.data.model.Book
import com.reksa.ebookshopcoroutine.data.model.Category


@Database(entities = [Category::class, Book::class], version = 1)
abstract class EBookDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        var INSTANCE: EBookDatabase? = null

        fun getDatabase(context: Context): EBookDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EBookDatabase::class.java,
                    "book_db.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}