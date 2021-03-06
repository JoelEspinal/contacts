package com.joelespinal.contacts.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.joelespinal.contacts.databinding.ContactItemBinding
import com.joelespinal.contacts.models.Contact
import com.joelespinal.contacts.ui.DetailActivity
import com.joelespinal.contacts.ui.main.MainViewModel
import com.squareup.picasso.Picasso


class ContactAdapter(private var context: Context?, private var mainViewModel: MainViewModel) :
    PagedListAdapter<Contact, ContactViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ContactItemBinding.inflate(layoutInflater, parent, false)
        return ContactViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact: Contact? = getItem(position)

        if (contact != null) {
            holder.binding.name.text = "${contact.first}"
            Picasso.get()
                .load(contact.thumbnail)
                .resize(200, 200)
                .centerCrop()
//            .placeholder(R.drawable.user_placeholder)
//            .error(R.drawable.user_placeholder_error)
                .into(holder.binding.picture)

            holder.binding.contactItem.setOnClickListener((View.OnClickListener {
                val launchIntent = Intent(context, DetailActivity::class.java)



                launchIntent.putExtra(DetailActivity.CONTACT_DETAIL, Gson().toJson(contact))

                context?.startActivity(launchIntent)
            }))
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Contact>() {
            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact) =
                oldItem.phone == newItem.phone

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: Contact, newItem: Contact
            ) = oldItem == newItem
        }
    }
}

class ContactViewHolder(var binding: ContactItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(contact: Contact) {
        binding.name.text = "${contact.first}"
    }
}