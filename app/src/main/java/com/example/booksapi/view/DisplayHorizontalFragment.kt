package com.example.booksapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksapi.common.parseBookList
import com.example.booksapi.databinding.DisplayFragmentLayoutBinding
import com.example.booksapi.model.remote.BookResponse
import com.example.booksapi.view.adapter.BookAdapter
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment representing a list of Items.
 */
class DisplayHorizontalFragment : Fragment() {

    private lateinit var binding: DisplayFragmentLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DisplayFragmentLayoutBinding.inflate(
            inflater,
            container,
            false
        )

        // Set the adapter
//        if (view is RecyclerView) {
//            with(view) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
//                adapter = MyBookItemRecyclerViewAdapter(PlaceholderContent.ITEMS)
//            }
//        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBooksResult.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        // LET Scope function
        // Receive T and return R
        // Unwrap a Optional/Nullable
        arguments?.getParcelable<BookResponse>(ARG_BOOK_RESPONSE)?.also {
            updateAdapter(it)
        }?: kotlin.run {
            showSnackBar()
        }

 //region Scope Functions Explanation
        // APPLY Scope Function
        // Receive T and return SAME T
        // Update/modify and object/instance
//        arguments?.getParcelable<BookResponse>(ARG_BOOK_RESPONSE)?.apply {
//        }
//
//        arguments?.getParcelable<BookResponse>(ARG_BOOK_RESPONSE).run {
//        }
//
//        arguments?.getParcelable<BookResponse>(ARG_BOOK_RESPONSE).also {
//        }
//endregion

    }

    private fun showSnackBar() {
        Snackbar.make(binding.root,"Unknown error",Snackbar.LENGTH_INDEFINITE)
            .setAction("Dismiss"){
                // todo destroy fragment
            }
            .show()
    }

    private fun updateAdapter(dataSet: BookResponse) {
        binding.rvBooksResult.adapter = BookAdapter(dataSet.items.parseBookList())

        arguments?.getInt(ARG_BOOK_POS)?.let {
            binding.rvBooksResult.scrollToPosition(it)
        }

    }

    companion object {

        const val ARG_BOOK_POS = "book_selected_index"
        const val ARG_BOOK_RESPONSE = "book_data_set"

        @JvmStatic
        fun newInstance(bookResponse: BookResponse, bookIndex: Int) =
            DisplayHorizontalFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_BOOK_POS, bookIndex)
                    putParcelable(ARG_BOOK_RESPONSE, bookResponse)

                }
            }
    }
}