package com.example.booksapi.view

interface Communicator {
    fun doSearch(bookTitle: String, filter: String, bookType: String , maxResults: Int)
}