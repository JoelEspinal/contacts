package com.joelespinal.contacts.ui.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.joelespinal.contacts.databinding.DetailFragmentBinding
import com.joelespinal.contacts.models.Contact
import com.joelespinal.contacts.ui.DetailActivity
import com.squareup.picasso.Picasso

class DetailFragment() : Fragment() {

    var contact: Contact? = null

    private lateinit var detailFragmentBinding: DetailFragmentBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailFragmentBinding = DetailFragmentBinding.inflate(inflater, container, false)
        return detailFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val bundle = activity?.intent?.extras
        val jsonContact = bundle?.getString(DetailActivity.CONTACT_DETAIL)
        if (jsonContact != null) {
            contact =
                Gson().fromJson(jsonContact, Contact::class.java)
            setUpLayout()
        }
    }

    private fun setUpLayout() {
        Picasso.get()
            .load(contact?.largePicture)
            .resize(200, 200)
            .centerCrop()
//            .placeholder(R.drawable.user_placeholder)
//            .error(R.drawable.user_placeholder_error)
            .into(detailFragmentBinding.profilePic)

        detailFragmentBinding.first.setText(contact?.first)
        detailFragmentBinding.lastName.setText(contact?.last)
        detailFragmentBinding.phone.setText(contact?.phone)
        detailFragmentBinding.cellPhone.setText(contact?.cell)
        detailFragmentBinding.saveFavorite.setOnClickListener((View.OnClickListener {
            viewModel.markFavorite(contact)
        }))
    }
}