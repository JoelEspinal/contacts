package com.joelespinal.contacts.repositories

import androidx.lifecycle.MutableLiveData
import com.joelespinal.contacts.models.Contact

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

    fun fetchContacts(): MutableLiveData<List<Contact>> {
        contacts.value = dummyContacts
        return contacts;
    }
}