package com.example.booksapi.model.remote

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class BookResponse(
    val items: List<BookItem>
): Parcelable

@Parcelize
data class BookItem(
    val  volumeInfo: BookInfo
): Parcelable

@Parcelize
data class BookInfo(
    val title: String = "",
    val subtitle: String = "",
    val authors: List<String> = emptyList(),
    val description: String = "",
    val publishedDate: String = "",
    val imageLinks: ImageLinks = ImageLinks("","")
): Parcelable

data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString().toString()
    ) {
    }

    //Marshal process
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(smallThumbnail)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    //Unmarshall process
    companion object CREATOR : Parcelable.Creator<ImageLinks> {
        override fun createFromParcel(parcel: Parcel): ImageLinks {
            return ImageLinks(parcel)
        }

        override fun newArray(size: Int): Array<ImageLinks?> {
            return arrayOfNulls(size)
        }
    }
}