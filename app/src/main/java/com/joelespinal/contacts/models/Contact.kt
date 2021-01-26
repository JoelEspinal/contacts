package com.joelespinal.contacts.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "first") var first: String?,
    @ColumnInfo(name = "last") var last: String?,
    @ColumnInfo(name = "phone") var phone: String?,
    @ColumnInfo(name = "cell") var cell: String?,
    @ColumnInfo(name = "thumbnail") var thumbnail: String? = "",
    @ColumnInfo(name = "largePicture") var largePicture: String? = ""
){
}
