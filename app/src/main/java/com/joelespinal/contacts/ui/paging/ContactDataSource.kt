package com.joelespinal.contacts.ui.paging

import androidx.paging.PageKeyedDataSource
import com.joelespinal.contacts.models.Contact
import com.joelespinal.contacts.remote.Retrofit
import com.joelespinal.contacts.remote.api.ContactApi
import com.joelespinal.contacts.remote.mapper.ContactMapper
import com.joelespinal.contacts.utils.ConnectivityHelper
import com.joelespinal.contacts.utils.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class ContactDataSource(private val scope: CoroutineScope) : PageKeyedDataSource<Int, Contact>() {

    private val contactApiService = Retrofit.instance.create(ContactApi::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Contact>
    ) {
        scope.launch {
            val currentPage = 1
            val nextPage = currentPage + 1

            if (ConnectivityHelper.getConnectionType() != Network.NONE) {
                val response = contactApiService.fetchUsers(page = currentPage, limit = 50)
                when {
                    response.isSuccessful -> {
                        val result = response.body()?.contacts
                        val contacts = result?.map { ContactMapper().convertContact(it) }
                        callback.onResult(contacts ?: listOf(), null, nextPage)
                    }
                }
            } else {
                callback.onResult(listOf(), null, nextPage)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Contact>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Contact>) {
        scope.launch {
            val currentPage = params.key
            val nextPage = currentPage + 1
            if (ConnectivityHelper.getConnectionType() != Network.NONE) {

                val response = contactApiService.fetchUsers(page = nextPage, limit = 50)
                when {
                    response.isSuccessful -> {
                        val result = response.body()?.contacts
                        val contacts = result?.map { ContactMapper().convertContact(it) }
                        callback.onResult(contacts ?: listOf(), nextPage)
                    }
                }
            } else {
                callback.onResult(listOf(), nextPage)
            }
        }
    }

}