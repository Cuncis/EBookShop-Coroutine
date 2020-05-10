package com.reksa.ebookshopcoroutine.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reksa.ebookshopcoroutine.R
import com.reksa.ebookshopcoroutine.adapter.EBookAdapter
import com.reksa.ebookshopcoroutine.data.model.Book
import com.reksa.ebookshopcoroutine.data.model.Category
import com.reksa.ebookshopcoroutine.viewmodel.EBookViewModel
import kotlinx.android.synthetic.main.activity_ebook.*

class EBookActivity : AppCompatActivity() {

    companion object {
        const val ADD_BOOK_REQUEST_CODE = 1
        const val EDIT_BOOK_REQUEST_CODE = 2
    }

    private lateinit var eBookViewModel: EBookViewModel
    private lateinit var eBookAdapter: EBookAdapter

    private var selectedCategory: Category? = null
    private var categoryList = arrayListOf<Category>()
    private var bookList = arrayListOf<Book>()

    private var selectedBookId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ebook)

        eBookAdapter = EBookAdapter()
        eBookViewModel = ViewModelProvider(this).get(EBookViewModel::class.java)

        observerViewModel()

        fab.setOnClickListener {
            val intent = Intent(this, AddAndEditActivity::class.java)
            startActivityForResult(intent, ADD_BOOK_REQUEST_CODE)
        }
    }

    private fun observerViewModel() {
        eBookViewModel.getAllCategories().observe(this, Observer {
            Log.d("_logEbook", "Data $it")
            categoryList.addAll(it)
            showSpinner()
        })
    }

    private fun showSpinner() {
        val categoryArrayAdapter = ArrayAdapter(this,
            R.layout.item_spinner, categoryList)
        categoryArrayAdapter.setDropDownViewResource(R.layout.item_spinner)
        spinner.adapter = categoryArrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do ur magic
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCategory = parent?.getItemAtPosition(position) as Category
                loadBookByCategory(selectedCategory!!.id)
            }

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
                val intent = Intent(this@EBookActivity, AddAndEditActivity::class.java)
                selectedBookId = book.bookId
                intent.putExtra(AddAndEditActivity.BOOK_ID, selectedBookId)
                intent.putExtra(AddAndEditActivity.BOOK_NAME, book.bookName)
                intent.putExtra(AddAndEditActivity.BOOK_PRICE, book.unitPrice)
                startActivityForResult(intent, EDIT_BOOK_REQUEST_CODE)
            }
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val bookToDelete = bookList[viewHolder.adapterPosition]
                eBookViewModel.deleteBook(bookToDelete)
            }
        }).attachToRecyclerView(rv_books)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val selectedCategoryId = selectedCategory?.id
        if (requestCode == ADD_BOOK_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val book = Book()
                book.categoryId = selectedCategoryId!!
                book.bookName = data?.getStringExtra(AddAndEditActivity.BOOK_NAME)!!
                book.unitPrice = data.getStringExtra(AddAndEditActivity.BOOK_PRICE)!!
                eBookViewModel.addNewBook(book)
            }
        } else if (requestCode == EDIT_BOOK_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val book = Book()
                book.categoryId = selectedCategoryId!!
                book.bookName = data?.getStringExtra(AddAndEditActivity.BOOK_NAME)!!
                book.unitPrice = data.getStringExtra(AddAndEditActivity.BOOK_PRICE)!!
                book.bookId = selectedBookId
                eBookViewModel.updateBook(book)
            }
        }
    }
}





















