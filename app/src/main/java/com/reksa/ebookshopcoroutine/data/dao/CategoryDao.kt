package com.reksa.ebookshopcoroutine.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reksa.ebookshopcoroutine.data.model.Category


@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT * FROM category_table")
    fun getAllCategories(): LiveData<List<Category>>

}