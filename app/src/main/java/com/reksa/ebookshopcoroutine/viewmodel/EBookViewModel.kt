package com.reksa.ebookshopcoroutine.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.reksa.ebookshopcoroutine.data.EBookRepository
import com.reksa.ebookshopcoroutine.data.model.Book
import com.reksa.ebookshopcoroutine.data.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EBookViewModel(application: Application): AndroidViewModel(application) {

    private val repository = EBookRepository(application)

    fun getAllCategories(): LiveData<List<Category>> {
        return repository.getCategories()
    }

    fun getBooksBySelectedCategory(categoryId: Int): LiveData<List<Book>> {
        return repository.getBooksByCategoryId(categoryId)
    }

    fun addNewBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertBook(book)
    }

    fun updateBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateBook(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteBook(book)
    }

}