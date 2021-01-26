package com.joelespinal.contacts.ui.dialogs

import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.database.DataSetObserver
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.joelespinal.contacts.R
import com.joelespinal.contacts.databinding.AddContactDialogBinding
import com.joelespinal.contacts.main.App
import com.joelespinal.contacts.models.Contact


class AddContactDialog : DialogFragment() {

    private lateinit var formDialogBinding: AddContactDialogBinding
    private lateinit var viewModel: DialogViewModel
    private var titles = ArrayList<String>()
    private var contactNames = ArrayList<String>()
    private lateinit var titleAdapter: ArrayAdapter<String>
    private lateinit var namesAdapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        formDialogBinding = AddContactDialogBinding.inflate(inflater, container, false)
        return formDialogBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DialogViewModel::class.java)

        formDialogBinding.toolbar.setNavigationOnClickListener { v -> dismiss() }
        formDialogBinding.toolbar.setTitle("New Contact")
        formDialogBinding.toolbar.inflateMenu(R.menu.save_contact_menu)
        checkPermission()
        setupTitles()

        formDialogBinding.firstField.setOnClickListener((View.OnClickListener {
            checkPermission()
        }))

        formDialogBinding.toolbar.setOnMenuItemClickListener { item ->
            saveContact()
            dismiss()
            true
        }
    }

    fun setupTitles() {
        titles = ArrayList(listOf("Dr", "Esq", "Hon", "Jr", "Mr", "Ms"))
        val dataSetObserver = object : DataSetObserver() {
            override fun onChanged() {
                super.onChanged()
            }
        }

        titleAdapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            titles
        )
        titleAdapter.registerDataSetObserver(dataSetObserver)

        formDialogBinding.titleDropDown.setAdapter(titleAdapter)

    }


    fun setupContactNames() {
        readContacts()
        val dataSetObserver = object : DataSetObserver() {
            override fun onChanged() {
                super.onChanged()
            }
        }
        namesAdapter = ArrayAdapter(requireContext(), R.layout.list_item, contactNames)
        namesAdapter.registerDataSetObserver(dataSetObserver)
        formDialogBinding.firstField.setAdapter(namesAdapter)


    }


    fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                App.getContext(),
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED -> {
                requestPermissions(listOf(Manifest.permission.READ_CONTACTS).toTypedArray(), 100)
            }
            else -> {
                readContacts()
                setupContactNames()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            readContacts()
        }
    }

    private fun readContacts() {
        val cursor =
            activity?.contentResolver?.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
            )
        if (cursor!!.moveToFirst()) {
            do {
                contactNames.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)))
            } while (cursor.moveToNext())

        }
    }

    fun saveContact() {
        val title = formDialogBinding.titleDropDown.text.toString()
        val firstName = formDialogBinding.firstField.text.toString()
        val lastName = formDialogBinding.lastField.text.toString()
        val phone = formDialogBinding.telField.text.toString()
        val cellPhone = formDialogBinding.cellFieldField.text.toString()


        val contact = Contact(
            0,
            title = title,
            first = firstName,
            last = lastName,
            phone = phone,
            cell = cellPhone
        )
        viewModel.saveContact(contact)
    }

}