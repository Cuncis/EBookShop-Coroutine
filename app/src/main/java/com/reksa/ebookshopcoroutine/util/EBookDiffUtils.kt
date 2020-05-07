package com.reksa.ebookshopcoroutine.util

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.reksa.ebookshopcoroutine.data.model.Book

class EBookDiffUtils(var oldBookList: ArrayList<Book>, var newBookList: ArrayList<Book>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldBookList.size
    }

    override fun getNewListSize(): Int {
        return newBookList.size
    }

    override fun areItemsTheSame(oldBookPosition: Int, newBookPosition: Int): Boolean {
        return oldBookList[oldBookPosition].bookId == newBookList[newBookPosition].bookId
    }

    override fun areContentsTheSame(oldBookPosition: Int, newBookPosition: Int): Boolean {
        return oldBookList[oldBookPosition] == newBookList[newBookPosition]
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newBookPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newBookPosition)
    }
}