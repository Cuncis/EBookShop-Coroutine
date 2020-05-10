package com.reksa.ebookshopcoroutine.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "category_table")
data class Category(

    @ColumnInfo(name = "category_name")
    var categoryName: String = "",

    @ColumnInfo(name = "category_description")
    var categoryDescription: String = ""

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {
        return categoryName
    }
}