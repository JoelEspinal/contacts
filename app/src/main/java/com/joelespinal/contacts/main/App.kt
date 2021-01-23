package com.joelespinal.contacts.main

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        private lateinit var appContext: Context

        fun setContext(context: Context) {
            this.appContext = context
        }

        fun getContext() = appContext
    }

    override fun onCreate() {
        super.onCreate()
        setContext(applicationContext)
    }
}