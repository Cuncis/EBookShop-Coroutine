package com.reksa.ebookshopcoroutine.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.reksa.ebookshopcoroutine.data.dao.BookDao
import com.reksa.ebookshopcoroutine.data.dao.CategoryDao
import com.reksa.ebookshopcoroutine.data.model.Book
import com.reksa.ebookshopcoroutine.data.model.Category

class EBookRepository(private val application: Application) {

    private val categoryDao: CategoryDao = EBookDatabase.getDatabase(application).categoryDao()
    private val bookDao: BookDao = EBookDatabase.getDatabase(application).bookDao()

    fun getCategories(): LiveData<List<Category>> {
        return categoryDao.getAllCategories()
    }

    fun getBooksByCategoryId(categoryId: Int): LiveData<List<Book>> {
        return bookDao.getBooksByCategoryId(categoryId)
    }

    suspend fun insertCategory(category: Category) = categoryDao.insert(category)

    suspend fun updateCategory(category: Category) = categoryDao.update(category)

    suspend fun deleteCategory(category: Category) = categoryDao.delete(category)

    suspend fun insertBook(book: Book) = bookDao.insert(book)

    suspend fun updateBook(book: Book) = bookDao.update(book)

    suspend fun deleteBook(book: Book) = bookDao.delete(book)
}