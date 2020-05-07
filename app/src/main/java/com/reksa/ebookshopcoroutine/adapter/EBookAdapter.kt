package com.reksa.ebookshopcoroutine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reksa.ebookshopcoroutine.R
import com.reksa.ebookshopcoroutine.data.model.Book
import com.reksa.ebookshopcoroutine.util.EBookDiffUtils
import kotlinx.android.synthetic.main.item_list_book.view.*

class EBookAdapter : RecyclerView.Adapter<EBookAdapter.EBookViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener
    private var bookList = ArrayList<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_book, parent, false)
        return EBookViewHolder(view)
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: EBookViewHolder, position: Int) {
        val book = bookList[position]
        holder.bookName.text = book.bookName
        holder.bookUnitPrice.text = book.unitPrice
    }

    fun setBooks(newList: ArrayList<Book>) {
        val result = DiffUtil.calculateDiff(EBookDiffUtils(bookList, newList), false)
        bookList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class EBookViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        var bookName: TextView = view.tv_bookName
        var bookUnitPrice: TextView = view.tv_price

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            itemClickListener.onItemClick(bookList[adapterPosition])
        }
    }

    interface OnItemClickListener {
        fun onItemClick(book: Book)
    }

}