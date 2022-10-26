package com.example.booksapi.model.remote

data class BookResponse(
    val items: List<BookItem>
)

data class BookItem(
    val  volumeInfo: List<BookInfo>
)

data class BookInfo(
    val title: String,
    val subtitle: String,
    val authors: List<String>,
    val description: String,
    val publishedDate: String,
    val imageLinks: ImageLinks
)

data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)