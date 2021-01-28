package com.joelespinal.contacts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joelespinal.contacts.R
import com.joelespinal.contacts.ui.ui.main.DetailFragment


class DetailActivity : AppCompatActivity() {

    companion object {

        const val CONTACT_DETAIL = "contact"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailFragment())
                .commitNow()
        }
    }
}