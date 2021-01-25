package com.joelespinal.contacts.repositories

import androidx.lifecycle.LiveData
import com.joelespinal.contacts.main.App
import com.joelespinal.contacts.models.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ContactRepository(private val scope: CoroutineScope) {

    private val app = App.getContext() as App
    private val userDao = app.db.userDao()
    lateinit var contactsLiveData: LiveData<List<Contact>>

    fun saveContact(contact: Contact) {
        scope.launch {
            userDao.insert(contact)
        }
    }

    init {
        scope.launch {
            contactsLiveData = userDao.getAll()

        }
    }
}