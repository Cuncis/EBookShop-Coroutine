package com.reksa.ebookshopcoroutine.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.reksa.ebookshopcoroutine.data.dao.BookDao
import com.reksa.ebookshopcoroutine.data.dao.CategoryDao
import com.reksa.ebookshopcoroutine.data.model.Book
import com.reksa.ebookshopcoroutine.data.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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
                ).addCallback(callback).build()
                INSTANCE = instance
                instance
            }
        }

        private val callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    val categoryDao = INSTANCE?.categoryDao()
                    val bookDao = INSTANCE?.bookDao()

                    val category1 = Category()
                    category1.categoryName = "Text Books"
                    category1.categoryDescription = "Text Books Description"

                    val category2 = Category()
                    category2.categoryName = "Novels"
                    category2.categoryDescription = "Novels Description"

                    val category3 = Category()
                    category3.categoryName = "Other Books"
                    category3.categoryDescription = "Other Description"

                    categoryDao?.insert(category1)
                    categoryDao?.insert(category2)
                    categoryDao?.insert(category3)

                    val book1 = Book()
                    book1.bookName = "High school Kotlin"
                    book1.unitPrice = "$150"
                    book1.categoryId = 1

                    val book2 = Book()
                    book2.bookName = "Mathematics for beginners"
                    book2.unitPrice = "$200"
                    book2.categoryId = 1

                    val book3 = Book()
                    book3.bookName = "Object Oriented Androd App Design"
                    book3.unitPrice = "$150"
                    book3.categoryId = 1

                    val book4 = Book()
                    book4.bookName = "Astrology for beginners"
                    book4.unitPrice = "$190"
                    book4.categoryId = 1

                    val book5 = Book()
                    book5.bookName = "High school Magic Tricks"
                    book5.unitPrice = "$150"
                    book5.categoryId = 1

                    val book6 = Book()
                    book6.bookName = "Chemistry  for secondary school students"
                    book6.unitPrice = "$250"
                    book6.categoryId = 1

                    val book7 = Book()
                    book7.bookName = "A Game of Cats"
                    book7.unitPrice = "$19.99"
                    book7.categoryId = 2

                    val book8 = Book()
                    book8.bookName = "The Hound of the New York"
                    book8.unitPrice = "$16.99"
                    book8.categoryId = 2

                    val book9 = Book()
                    book9.bookName = "Adventures of Joe Finn"
                    book9.unitPrice = "$13"
                    book9.categoryId - 2

                    val book10 = Book()
                    book10.bookName = "Arc of witches"
                    book10.unitPrice = "$19.99"
                    book10.categoryId = 2

                    val book11 = Book()
                    book11.bookName = "Can I run"
                    book11.unitPrice = "$16.99"
                    book11.categoryId = 2

                    val book12 = Book()
                    book12.bookName ="Story of a joker"
                    book12.unitPrice = "$13"
                    book12.categoryId = 2

                    val book13 = Book()
                    book13.bookName = "Notes of a alien life cycle researcher"
                    book13.unitPrice = "$1250"
                    book13.categoryId = 3

                    val book14 = Book()
                    book14.bookName = "Top 9 myths abut UFOs"
                    book14.unitPrice = "$789"
                    book14.categoryId = 3

                    val book15 = Book()
                    book15.bookName = "How to become a millionaire in 24 hours"
                    book15.unitPrice = "$1250"
                    book15.categoryId = 3

                    val book16 = Book()
                    book16.bookName = "1 hour work month"
                    book16.unitPrice = "$199"
                    book16.categoryId = 3

                    bookDao?.insert(book1);
                    bookDao?.insert(book2);
                    bookDao?.insert(book3);
                    bookDao?.insert(book4);
                    bookDao?.insert(book5);
                    bookDao?.insert(book6);
                    bookDao?.insert(book7);
                    bookDao?.insert(book8);
                    bookDao?.insert(book9);
                    bookDao?.insert(book10);
                    bookDao?.insert(book11);
                    bookDao?.insert(book12);
                    bookDao?.insert(book13);
                    bookDao?.insert(book14);
                    bookDao?.insert(book15);
                    bookDao?.insert(book16);
                }
            }
        }

    }

}