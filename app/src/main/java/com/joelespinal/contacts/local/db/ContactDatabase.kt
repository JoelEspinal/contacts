package com.joelespinal.contacts.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joelespinal.contacts.local.db.daos.ContactDao
import com.joelespinal.contacts.models.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun userDao(): ContactDao
}