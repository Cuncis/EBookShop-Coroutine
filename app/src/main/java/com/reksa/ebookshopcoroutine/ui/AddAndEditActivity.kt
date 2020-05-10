package com.reksa.ebookshopcoroutine.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.reksa.ebookshopcoroutine.R
import com.reksa.ebookshopcoroutine.data.model.Book
import kotlinx.android.synthetic.main.activity_add_and_edit.*

class AddAndEditActivity : AppCompatActivity() {

    companion object {
        const val BOOK_ID = "bookId"
        const val BOOK_NAME = "bookName"
        const val BOOK_PRICE = "unitPrice"
    }

    private var book = Book()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_and_edit)

        if (intent.hasExtra(BOOK_ID)) {
            title = "Edit Book"
            book.bookName = intent.getStringExtra(BOOK_NAME)!!
            book.unitPrice = intent.getStringExtra(BOOK_PRICE)!!

            et_bookName.setText(intent.getStringExtra(BOOK_NAME))
            et_unitPrice.setText(intent.getStringExtra(BOOK_PRICE))
        } else {
            title = "New Book"
        }

        btn_addAndEdit.setOnClickListener {
            book.bookName = et_bookName.text.toString()
            book.unitPrice = et_unitPrice.text.toString()

            if (TextUtils.isEmpty(et_bookName.text.toString()) or TextUtils.isEmpty(et_unitPrice.text.toString())) {
                Toast.makeText(this, "The Field can't be null", Toast.LENGTH_SHORT).show();
            } else {
                val intent = Intent()
                intent.putExtra(BOOK_NAME, book.bookName)
                intent.putExtra(BOOK_PRICE, book.unitPrice)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}
