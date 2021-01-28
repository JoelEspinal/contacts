package com.joelespinal.contacts.ui.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joelespinal.contacts.models.Contact
import com.joelespinal.contacts.repositories.ContactRepository

class DetailViewModel : ViewModel() {


    private val contactRepository = ContactRepository(viewModelScope)

    fun markFavorite(contact: Contact?) {
        if (contact != null)
            contactRepository.saveContact(contact)
    }
}