package com.joelespinal.contacts.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joelespinal.contacts.databinding.ContactItemBinding
import com.joelespinal.contacts.models.Contact
import com.squareup.picasso.Picasso

class SavedContactAdapter(private var contacts: List<Contact>) :
    RecyclerView.Adapter<SavedContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(var binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.name.text = "${contact.first}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ContactItemBinding.inflate(layoutInflater, parent, false)
        return ContactViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        if (contact != null) {
            holder.binding.name.text = "${contact.first}"
            if (!contact.thumbnail?.isNullOrEmpty()!!) {
                Picasso.get()
                    .load(contact.thumbnail)
                    .resize(200, 200)
                    .centerCrop()
//                    .placeholder(R.drawable.ic_launcher_foreground)
//                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.binding.picture)
            }

        }
    }

    override fun getItemCount(): Int = contacts.size

    fun updateContacts(contactList: List<Contact>) {
        contacts = contactList
        notifyDataSetChanged()
    }
}