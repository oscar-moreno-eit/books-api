package com.example.booksapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.booksapi.model.remote.BookNetwork
import com.example.booksapi.model.remote.BookResponse
import com.example.booksapi.view.Communicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun doSearch(bookTitle: String, filter: String, bookType: String, maxResults: Int) {
        BookNetwork.bookApi.getBooksByFilters(bookTitle,filter,maxResults, bookType)
            .enqueue(
                object : Callback<BookResponse>{
                    override fun onResponse(
                        call: Call<BookResponse>,
                        response: Response<BookResponse>
                    ) {
                        if (response.isSuccessful){
                            val body = response.body()
                            createDisplayFragment(body)
                        }
                    }

                    override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                }
            )  //execute - syncronous : enqueue - Asyncronous
    }

    private fun createDisplayFragment(body: BookResponse?) {
        body?.let {
            supportFragmentManager
        }
    }
}