package com.joelespinal.contacts.remote.response

import com.google.gson.annotations.SerializedName

class ContactsResponse {

    @SerializedName("results")
    var contacts: MutableList<ContactResponse>? = null
}