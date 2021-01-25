package com.joelespinal.contacts.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.joelespinal.contacts.models.Contact
import com.joelespinal.contacts.repositories.ContactRepository
import com.joelespinal.contacts.ui.paging.ContactSourceFactory


class MainViewModel() : ViewModel() {
    private val contactDataSourceFactory = ContactSourceFactory(viewModelScope)
    private val contactRepository = ContactRepository(viewModelScope)

    fun getContactsLiveData(): LiveData<PagedList<Contact>> {
        return contactDataSourceFactory.contactsLiveData
    }

    fun refresh() {
        contactDataSourceFactory.contactsLiveData.value?.dataSource?.invalidate()
    }

    fun getSavedContactsLiveData(): LiveData<List<Contact>> {
        return contactRepository.contactsLiveData
    }

    fun saveContact(contact: Contact) {
        contactRepository.saveContact(contact)
    }
}
