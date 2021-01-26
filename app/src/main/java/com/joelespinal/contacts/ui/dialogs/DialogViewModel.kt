package com.joelespinal.contacts.ui.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joelespinal.contacts.models.Contact
import com.joelespinal.contacts.repositories.ContactRepository

class DialogViewModel : ViewModel() {

    private val contactRepository = ContactRepository(viewModelScope)

    fun saveContact(contact: Contact) {
        contactRepository.saveContact(contact)
    }
}