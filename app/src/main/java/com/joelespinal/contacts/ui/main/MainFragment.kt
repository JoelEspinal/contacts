package com.joelespinal.contacts.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.joelespinal.contacts.databinding.MainFragmentBinding
import com.joelespinal.contacts.ui.adapters.ContactAdapter
import com.joelespinal.contacts.ui.adapters.GridSpacingItemDecoration

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var mainFragmentBinding: MainFragmentBinding
    private lateinit var contactsAdapter: ContactAdapter
    var spanCount = 3
    var spacing = 50
    var includeEdge = true

    private var contactLayoutManager = GridLayoutManager(context, spanCount)

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)
        return mainFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupRecycleView()
    }

    private fun setupRecycleView() {
        val contactsRecycleView = mainFragmentBinding.contactsRecycleView
        contactsRecycleView.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount,
                spacing,
                includeEdge
            )
        )

        contactsRecycleView.layoutManager = contactLayoutManager
        contactsAdapter = ContactAdapter()
        contactsRecycleView.adapter = contactsAdapter
        mainFragmentBinding.contactsRecycleView.adapter = contactsAdapter

        val contactLiveData = viewModel.getContactsLiveData()

        contactLiveData.observe(this, {
            updateView(it.size);
            contactsAdapter.submitList(it)
        })

        mainFragmentBinding.refresh.setOnClickListener((View.OnClickListener {
            viewModel.refresh()
        }))
    }

    private fun updateView(itemCount: Int) {
//        if (itemCount > 0) {
//            mainFragmentBinding.contactsRecycleView.visibility = View.VISIBLE
        mainFragmentBinding.todoListEmptyView.visibility = View.GONE
//        } else {
//            mainFragmentBinding.contactsRecycleView.visibility = View.GONE
//            mainFragmentBinding.todoListEmptyView.visibility = View.VISIBLE
//        }
    }
}