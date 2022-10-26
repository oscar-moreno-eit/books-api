package com.example.booksapi.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// q=last wish
// &filter=ebooks
// &maxResults=1
// &printType=books

interface BookAPI {
    @GET(BookNetwork.ENDPOINT)
    fun getBooksByFilters(
        @Query("q") bookTitle: String,
        @Query("filter") bookFilter: String,
        @Query("maxResults") bookMaxResults: Int,
        @Query("printType") bookPrintType: String

    ): Call<BookResponse>
}