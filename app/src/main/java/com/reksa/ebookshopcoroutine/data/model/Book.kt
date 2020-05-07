package com.reksa.ebookshopcoroutine.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey


@Entity(tableName = "book_table", foreignKeys = (arrayOf(
    ForeignKey(
    entity = Category::class,
        onDelete = CASCADE,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("category_id"))))
)
data class Book(

    @ColumnInfo(name = "book_name")
    var bookName: String = "",

    @ColumnInfo(name = "unit_price")
    var unitPrice: String = "",

    @ColumnInfo(name = "category_id")
    var categoryId: Int = 0

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    var bookId: Int = 0
}