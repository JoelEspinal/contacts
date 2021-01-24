package com.joelespinal.contacts.ui.paging

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.joelespinal.contacts.models.Contact
import kotlinx.coroutines.CoroutineScope

class ContactSourceFactory(private val scope: CoroutineScope) : DataSource.Factory<Int, Contact>() {
    private var contactDataSource = ContactDataSource(scope)
    var contactsLiveData: LiveData<PagedList<Contact>>
    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(5)
        .setPageSize(5)
        .build()

    override fun create(): DataSource<Int, Contact> {
        return contactDataSource
    }

    init {
        contactsLiveData = LivePagedListBuilder(this, config).build()
    }


}