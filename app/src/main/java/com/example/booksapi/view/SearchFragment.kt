package com.example.booksapi.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.booksapi.MainActivity
import com.example.booksapi.R
import com.example.booksapi.databinding.SearchFragmentLayoutBinding
import com.google.android.material.textfield.TextInputLayout

class SearchFragment: Fragment() {
    private lateinit var binding: SearchFragmentLayoutBinding
    private lateinit var bridge: Communicator

    /**
     * context here is the host activity, in this case: MainActivity
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context){
            is MainActivity -> bridge = context
            else -> IllegalAccessException("Incorrect  Host Activity!")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SearchFragmentLayoutBinding.inflate(inflater,container,false )
        //Traditional non-binding
//        val view = inflater.inflate(
//            R.layout.search_fragment_layout,container,false
//        )
//        view.findViewById<TextInputLayout>(R.id.til_book_search)
        initViews()
        return  binding.root
    }

    private fun initViews() {
        binding.btnSearchBook.setOnClickListener {
            bridge.doSearch(binding.tilBookSearch.editText?.text.toString(),binding.spFilter.selectedItem.toString(),binding.spBookType.selectedItem.toString(),binding.sbMaxResults.progress)
        }
    }

}