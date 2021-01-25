package com.joelespinal.contacts.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "first") val first: String?,
    @ColumnInfo(name = "last") val last: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "cell") val cell: String?,
    @ColumnInfo(name = "thumbnail") val thumbnail: String?,
    @ColumnInfo(name = "largePicture") val largePicture: String?
)
