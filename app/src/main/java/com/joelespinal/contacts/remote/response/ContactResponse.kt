package com.joelespinal.contacts.remote.response

import com.google.gson.annotations.SerializedName

class ContactResponse {
    @SerializedName("name")
    var name: Name? = null
    @SerializedName("phone")
    var phone: String? = null
    @SerializedName("cell")
    var cell: String? = null
    @SerializedName("picture")
    var picture: Picture? = null
}

class Name {
    @SerializedName("title")
    var title: String = ""
    @SerializedName("first")
    var first: String = ""
    @SerializedName("last")
    var last: String = ""
}

class Picture {
    @SerializedName("medium")
    var medium: String? = null
    @SerializedName("large")
    var large: String? = null
}