package com.joelespinal.contacts.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joelespinal.contacts.models.Contact
import com.joelespinal.contacts.repositories.ContactRepository

class MainViewModel() : ViewModel() {

    private val contactRepository = ContactRepository()

    fun fetchContacts(): MutableLiveData<List<Contact>> {
        return contactRepository.fetchContacts()
    }
}
