package com.example.booksapi.view.adapter

import android.R
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapi.databinding.BookItemLayoutVerticalBinding
import com.example.booksapi.model.remote.BookInfo
import com.squareup.picasso3.Picasso
import java.net.URL


class BookAdapter(private val dataset: List<BookInfo>,private val openDetails: (BookInfo) -> Unit) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(private val binding: BookItemLayoutVerticalBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(currentElement: BookInfo,openDetails: (BookInfo) -> Unit){
            binding.root.setOnClickListener {
                openDetails( currentElement)
            }
            binding.tvBookTitle.text = currentElement.title
            binding.tvBookPublished.text = currentElement.publishedDate

            // todo implmement picasso and finish the Picasso binding
            // get Picasso Dependency, do the Picasso.Builder().load().into(binding.ivCoverBook)

            Picasso.Builder(binding.ivBookCover.context).build().load(currentElement.imageLinks.smallThumbnail.replace("http:","https:")).into(binding.ivBookCover)

//            val photoUrl = URL(currentElement.imageLinks.smallThumbnail)
//            binding.ivBookCover.setImageBitmap(BitmapFactory.decodeStream(photoUrl.openConnection().getInputStream()))

//            val imageUri = "https://g.christianbook.com/dg/product/cbd/f400/160341.jpg"
//            Picasso.Builder(binding.ivBookCover.context).build().load(imageUri).into(binding.ivBookCover)


        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(BookItemLayoutVerticalBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(dataset[position],openDetails)
    }

    override fun getItemCount(): Int {
        return dataset.count()
    }
}