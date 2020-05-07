package com.reksa.ebookshopcoroutine.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reksa.ebookshopcoroutine.data.model.Book


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query("SELECT * FROM book_table")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE category_id LIKE :categoryId")
    fun getBooksByCategoryId(categoryId: Int): LiveData<List<Book>>

}