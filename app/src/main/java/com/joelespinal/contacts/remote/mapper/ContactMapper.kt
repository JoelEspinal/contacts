package com.joelespinal.contacts.remote.mapper

import com.joelespinal.contacts.models.Contact
import com.joelespinal.contacts.remote.response.ContactsResponse
import com.joelespinal.contacts.remote.response.ContactResponse
import com.joelespinal.contacts.remote.response.Name
import com.joelespinal.contacts.remote.response.Picture

class ContactMapper {

    fun convertContacts(contactsResponse: ContactsResponse): MutableList<Contact> {
        var models: MutableList<Contact> = ArrayList()

        contactsResponse.contacts?.map {
            models.add(convertContact(it))
        }

        return models
    }

    fun convertContact(contactResponse: ContactResponse): Contact {
        var name: Name? = contactResponse.name
        var picture: Picture? = contactResponse.picture

        return Contact(
            title = name?.title,
            first = name?.first,
            last = name?.last,
            phone = contactResponse.phone,
            cell = contactResponse.cell,
            thumbnail = picture?.medium,
            largePicture = picture?.large
        )
    }


}