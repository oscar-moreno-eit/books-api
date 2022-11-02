package com.example.booksapi.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.booksapi.MainActivity
import com.example.booksapi.R
import com.example.booksapi.databinding.SearchFragmentLayoutBinding

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
        binding.btnSearchBook.setOnClickListener{

            if (binding.sbMaxResults.progress == 0) {binding.sbMaxResults.progress = 10}

            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(binding.root.windowToken, 0)

            bridge.doSearch(binding.tilBookSearch.editText?.text.toString()
                ,binding.spFilter.selectedItem.toString()
                ,binding.spBookType.selectedItem.toString()
                ,binding.sbMaxResults.progress)
        }
        binding.spFilter.adapter = ArrayAdapter<String>(requireContext()
            ,android.R.layout.simple_list_item_1
            ,requireContext().resources.getStringArray(R.array.sp_filter)
        )
        context?.let {
            binding.spBookType.adapter = ArrayAdapter<String>(it
                ,android.R.layout.simple_list_item_1
                ,it.resources.getStringArray(R.array.sp_print_type)
            )
        }
    }

}