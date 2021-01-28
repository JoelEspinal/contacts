package com.joelespinal.contacts.utils

import com.joelespinal.contacts.models.Contact
import kotlinx.serialization.json.Json
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class ParseContact {
    companion object {
        fun toObject(stringValue: String): Contact {
            return Json.decodeFromString(Contact.serializer(), stringValue)
        }

        fun toJson(field: Contact): String {
            return Json.encodeToString(Contact.serializer(), field)
        }

        fun serialize(contact: Contact) {
            var serializedObject = ""
            try {
                val bo = ByteArrayOutputStream()
                val so = ObjectOutputStream(bo)
                so.writeObject(contact)
                so.flush()
                serializedObject = bo.toString()
            } catch (e: Exception) {
                println(e)
            }
        }

        fun deserialize(serializedObject: String): Contact? {
            var contact: Contact? = null
            try {
                val b: ByteArray = serializedObject.toByteArray()
                val bi = ByteArrayInputStream(b)
                val si = ObjectInputStream(bi)
                val contact = si.readObject() as Contact
            } catch (e: java.lang.Exception) {
                println(e)
            }

            return contact
        }
    }

}