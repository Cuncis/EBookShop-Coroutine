package com.reksa.ebookshopcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reksa.ebookshopcoroutine.adapter.EBookAdapter
import com.reksa.ebookshopcoroutine.data.model.Book
import com.reksa.ebookshopcoroutine.data.model.Category
import com.reksa.ebookshopcoroutine.viewmodel.EBookViewModel
import kotlinx.android.synthetic.main.activity_ebook.*

class EBookActivity : AppCompatActivity() {

    private lateinit var eBookViewModel: EBookViewModel
    private lateinit var eBookAdapter: EBookAdapter

    private var categoryList = arrayListOf<Category>()
    private var bookList = arrayListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ebook)

        eBookAdapter = EBookAdapter()
        eBookViewModel = ViewModelProvider(this).get(EBookViewModel::class.java)


        observerViewModel()

    }

    private fun observerViewModel() {
        eBookViewModel.getAllCategories().observe(this, Observer {
            categoryList = it as ArrayList<Category>
            showSpinner()
        })
    }

    private fun showSpinner() {
        val categoryArrayAdapter = ArrayAdapter(this, R.layout.item_spinner, categoryList)
        categoryArrayAdapter.setDropDownViewResource(R.layout.item_spinner)
        spinner.adapter = categoryArrayAdapter
        spinner.setOnItemClickListener { parent, _, position, _ ->
            val category = parent.getItemAtPosition(position) as Category
            loadBookByCategory(category.id)
        }
    }

    private fun loadBookByCategory(categoryId: Int) {
        eBookViewModel.getBooksBySelectedCategory(categoryId).observe(this, Observer {
            bookList = it as ArrayList<Book>
            loadRecyclerView()
        })
    }

    private fun loadRecyclerView() {
        rv_books.apply {
            layoutManager = LinearLayoutManager(this@EBookActivity)
            setHasFixedSize(true)
            adapter = eBookAdapter
        }
        eBookAdapter.setBooks(bookList)
        eBookAdapter.setItemClickListener(object : EBookAdapter.OnItemClickListener {
            override fun onItemClick(book: Book) {

            }
        })
    }
}
