package com.joelespinal.contacts.local.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joelespinal.contacts.models.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact")
    fun getAll(): LiveData<List<Contact>>

    @Insert
    suspend fun insert(contact: Contact)
}