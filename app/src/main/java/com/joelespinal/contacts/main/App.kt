package com.joelespinal.contacts.main

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.joelespinal.contacts.local.db.ContactDatabase

class App : Application() {

    lateinit var db: ContactDatabase


    override fun onCreate() {
        super.onCreate()

        App.Companion.setContext(applicationContext)
        db = Room.databaseBuilder(
           this,
            ContactDatabase::class.java, "contacts_database"
        ).build()
    }

    companion object {
        private lateinit var appContext: Context

        fun setContext(context: Context) {
            appContext = context
        }

        fun getContext(): Context {
            return appContext
        }
    }
}