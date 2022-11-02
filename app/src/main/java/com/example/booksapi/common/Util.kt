package com.example.booksapi.common

import com.example.booksapi.model.remote.BookInfo
import com.example.booksapi.model.remote.BookItem

fun List<BookItem>.parseBookList() =
    map { bookItem ->
        BookInfo(
            bookItem.volumeInfo.title
            ,bookItem.volumeInfo.subtitle
            ,bookItem.volumeInfo.authors
            ,bookItem.volumeInfo.description
            ,bookItem.volumeInfo.publishedDate
            ,bookItem.volumeInfo.imageLinks
        )
    }