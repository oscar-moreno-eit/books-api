package com.example.booksapi.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * In kotlin this is a Singleton
 * An object that exist in memory
 * It doesn't need an instance/constructor
 */
object BookNetwork {
    /* Retrofit
    *  1.- Create an API Interface
    *  2.- Define the HTTP Verbs in that reference
    *  3.- Define Base URL, Endpoints
    *  4.- Create the Retrofit Object
    *  5.- Create the API Interface from the Retrofit Object
    * */
    const val BASE_URL = "https://www.googleapis.com/"
    const val ENDPOINT = "books/v1/volumes"

    val bookApi: BookAPI by lazy{ // Create a temporary class
        initRetrofit().create(BookAPI::class.java)
    }

    private fun initRetrofit(): Retrofit{
        val retrofitObject = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return  retrofitObject
    }

}