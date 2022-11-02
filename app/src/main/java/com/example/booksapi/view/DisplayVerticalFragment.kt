package com.example.booksapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksapi.R
import com.example.booksapi.common.parseBookList
import com.example.booksapi.databinding.DisplayFragmentLayoutBinding
import com.example.booksapi.model.remote.BookInfo
import com.example.booksapi.model.remote.BookResponse
import com.example.booksapi.view.adapter.BookAdapter

private const val TAG = "DisplayVerticalFragment"

class DisplayVerticalFragment:Fragment() {

    companion object{ //Factory design pattern - a single method
        const val DISPLAY_BOOK = "DISPLAY_BOOK"
        fun newInstance(bookResponse: BookResponse): DisplayVerticalFragment{
            val fragment = DisplayVerticalFragment()
            val bundle = Bundle()
            bundle.putParcelable(DISPLAY_BOOK, bookResponse)
            fragment.arguments = bundle
            return  fragment
        }
    }

    private lateinit var binding: DisplayFragmentLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DisplayFragmentLayoutBinding.inflate(inflater,container,false)
        initViews()
        arguments?.getParcelable<BookResponse>(DISPLAY_BOOK)?.let {
            updateAdapter(it)
        }
        return binding.root
    }

    private fun updateAdapter(dataSet: BookResponse) {
        binding.rvBooksResult.adapter = BookAdapter(parseListBookInfo(dataSet)){ indexPos ->
            // Trailing lambda
            Toast.makeText(context,"$indexPos",Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction()
                .replace(R.id.display_horizontal_container,DisplayHorizontalFragment.newInstance(dataSet,indexPos))
                .addToBackStack(null)
                .commit()

        }
    }

    private fun parseListBookInfo(dataSet: BookResponse): List<BookInfo> {
        return dataSet.items.parseBookList()
    }

    private fun  initViews(){
        binding.rvBooksResult.layoutManager = LinearLayoutManager(context)
        //binding.rvBooksResult.adapter = BookAdapter(emptyList(),::navigateDetails) // :: = Method reference

    }

    private fun navigateDetails(bookInfo: BookInfo) {

    }

}