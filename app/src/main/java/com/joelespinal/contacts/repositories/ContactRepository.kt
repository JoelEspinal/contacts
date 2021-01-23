package com.joelespinal.contacts.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.joelespinal.contacts.models.Contact
import com.joelespinal.contacts.remote.Retrofit
import com.joelespinal.contacts.remote.api.ContactApi
import com.joelespinal.contacts.remote.mapper.ContactMapper
import com.joelespinal.contacts.utils.ConnectivityHelper
import com.joelespinal.contacts.utils.Network
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import java.net.HttpURLConnection

class ContactRepository {
    private val dummyContacts = listOf(
        Contact(
            title = "Mr.", first = "Joel", last = "Espinal",
            phone = "809-579-5555", cell = "829-444-5555",
            thumbnail = "https://randomuser.me/api/portraits/med/men/75.jpg",
            largePicture = "https://randomuser.me/api/portraits/men/75.jpg",
        ),
        Contact(
            title = "Mr.", first = "German", last = "Espinal",
            phone = "809-588-5555", cell = "829-444-5445",
            thumbnail = "https://randomuser.me/api/portraits/med/men/75.jpg",
            largePicture = "https://randomuser.me/api/portraits/men/75.jpg",
        ),
        Contact(
            title = "Mr.", first = "German", last = "Espinal",
            phone = "809-588-5555", cell = "829-444-5445",
            thumbnail = "https://randomuser.me/api/portraits/med/men/75.jpg",
            largePicture = "https://randomuser.me/api/portraits/men/75.jpg",
        ),
        Contact(
            title = "Mr.", first = "German", last = "Espinal",
            phone = "809-588-5555", cell = "829-444-5445",
            thumbnail = "https://randomuser.me/api/portraits/med/men/75.jpg",
            largePicture = "https://randomuser.me/api/portraits/men/75.jpg",
        ),
        Contact(
            title = "Mr.", first = "German", last = "Espinal",
            phone = "809-588-5555", cell = "829-444-5445",
            thumbnail = "https://randomuser.me/api/portraits/med/men/75.jpg",
            largePicture = "https://randomuser.me/api/portraits/men/75.jpg",
        ),
        Contact(
            title = "Mr.", first = "German", last = "Espinal",
            phone = "809-588-5555", cell = "829-444-5445",
            thumbnail = "https://randomuser.me/api/portraits/med/men/75.jpg",
            largePicture = "https://randomuser.me/api/portraits/men/75.jpg",
        ),
    )

    private val contacts = MutableLiveData<List<Contact>>()
    private val contactApiService = Retrofit.instance.create(ContactApi::class.java)

    fun fetchContacts(): MutableLiveData<List<Contact>> {
//        contacts.value = dummyContacts

        if (ConnectivityHelper.getConnectionType() != Network.NONE) {
            try {
                GlobalScope.launch(Dispatchers.Main) {
                    val response = withContext(Dispatchers.IO) {
                        contactApiService.fetchUsers(limit = 5)
                    }

                    if (response.isSuccessful && response.body() != null) {
                        val result = ContactMapper().convertContacts(response.body()!!)
                        contacts.postValue(result)
                    } else {
                        var errorMessage = when (response.code()) {
                            HttpURLConnection.HTTP_NOT_FOUND -> "ups, Service not found"
                            HttpURLConnection.HTTP_CLIENT_TIMEOUT -> "Time out error"
                            HttpURLConnection.HTTP_UNAVAILABLE -> "Server Unavailable"
                            else -> "Unknown error has occurs"
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("ERROR_CALL", e.message!!)
            }
        }
        return contacts
    }
}