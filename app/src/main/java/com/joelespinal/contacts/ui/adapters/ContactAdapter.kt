package com.joelespinal.contacts.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joelespinal.contacts.databinding.ContactItemBinding
import com.joelespinal.contacts.models.Contact

class ContactAdapter(private var contacts: MutableList<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private lateinit var recyclerView: RecyclerView

    inner class ContactViewHolder(var binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.name.text = "${contact.first}"
            binding.last.text = "${contact.last}"
            binding.phone.text = "${contact.cell}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ContactItemBinding.inflate(layoutInflater, parent, false)
        return ContactViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]

        holder.binding.name.text = "${contact.first}"
        holder.binding.last.text = "${contact.last}"
        holder.binding.phone.text = "${contact.cell}"
    }

    override fun getItemCount(): Int = contacts.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    fun updateContacts(contacts: MutableList<Contact>) {
        this.contacts = contacts
    }
}
