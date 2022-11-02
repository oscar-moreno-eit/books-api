package com.example.booksapi.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapi.R
import com.example.booksapi.databinding.BookItemLayoutVerticalBinding
import com.example.booksapi.databinding.ItemHorizontalLayoutBinding
import com.example.booksapi.model.remote.BookInfo
import com.squareup.picasso3.Picasso



class BookAdapter(private val dataset: List<BookInfo>, private val openDetails: ((Int) -> Unit)? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class BookViewHolder(private val binding: BookItemLayoutVerticalBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(currentElement: BookInfo,openDetails: (Int) -> Unit){
            binding.root.setOnClickListener {
                openDetails(bindingAdapterPosition )
            }
            binding.tvBookTitle.text = currentElement.title
            binding.tvBookPublished.text = currentElement.publishedDate

            Picasso.Builder(binding.ivBookCover.context).build().load(currentElement.imageLinks.smallThumbnail.replace("http:","https:")).into(binding.ivBookCover)

        }
    }
    class BookViewHolderHorizontal(private val binding: ItemHorizontalLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(currentElement: BookInfo){


            binding.tvBookTitle.text = currentElement.title
            binding.tvBookSubtitle.text = currentElement.subtitle
            binding.tvBookAuthor.text = currentElement.authors.toString().replace("[","").replace("]","")
            binding.tvDescription.text = currentElement.description
            binding.tvBookPublished.text = currentElement.publishedDate
            Picasso.Builder(binding.ivBookCover.context).build().load(currentElement.imageLinks.smallThumbnail.replace("http:","https:").replace("&zoom=1","&zoom=2")).into(binding.ivBookCover)

            binding.btShowDescription.setOnClickListener {
                binding.btShowDescription.background = null

                if (binding.vgMainGroup.visibility == View.VISIBLE ){
                    binding.vgMainGroup.visibility = View.GONE
                    (binding.tvBookTitle.layoutParams as ConstraintLayout.LayoutParams).apply {
                        bottomToTop = binding.svDescription.id
                    }
                    (binding.svDescription.layoutParams as ConstraintLayout.LayoutParams).apply {
                        topToBottom = binding.tvBookTitle.id
                        height = 0
                    }

                    /* Note: You can set a background color and an image resource at the same time but you  cannot set one background color neither a background image at the same time */
                    binding.btShowDescription.setBackgroundColor(Color.LTGRAY)
                    binding.btShowDescription.setImageResource(R.drawable.ic_baseline_keyboard_double_arrow_up_24)
                }
                else {
                    binding.vgMainGroup.visibility = View.VISIBLE
                    binding.btShowDescription.setBackgroundColor(Color.LTGRAY)
                    binding.btShowDescription.setImageResource( R.drawable.ic_baseline_keyboard_double_arrow_down_24)

                    (binding.tvBookTitle.layoutParams as ConstraintLayout.LayoutParams).apply {
                        bottomToTop = binding.tvBookSubtitle.id
                    }
                    (binding.svDescription.layoutParams as ConstraintLayout.LayoutParams).apply {
                        topToBottom = binding.tvBookPublished.id
                        height = 260
                    }
                }
            }



//            val photoUrl = URL(currentElement.imageLinks.smallThumbnail)
//            binding.ivBookCover.setImageBitmap(BitmapFactory.decodeStream(photoUrl.openConnection().getInputStream()))

//            val imageUri = "https://g.christianbook.com/dg/product/cbd/f400/160341.jpg"
//            Picasso.Builder(binding.ivBookCover.context).build().load(imageUri).into(binding.ivBookCover)


        }
    }

    //Optional, just when implementing multiple viewholders
    /**
     * Defines which "view type" for the  given index position of that dataset
     */
    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        //return if (openDetails == null) BookListType.BOOK_DETAIL_HORIZONTAL.ordinal else BookListType.BOOK_RESULT_VERTICAL.ordinal
        return openDetails?.let { BookListType.BOOK_RESULT_VERTICAL.ordinal }?:  BookListType.BOOK_DETAIL_HORIZONTAL.ordinal
    }

    private enum class BookListType{
        BOOK_RESULT_VERTICAL, BOOK_DETAIL_HORIZONTAL
    }


//viewType
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            BookListType.BOOK_RESULT_VERTICAL.ordinal -> {    BookViewHolder(BookItemLayoutVerticalBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            BookListType.BOOK_DETAIL_HORIZONTAL.ordinal -> {  BookViewHolderHorizontal(ItemHorizontalLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> throw Exception("Incorrect ViewHolder")
        }

    }

    //holder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is BookViewHolder -> {
                openDetails?.let {
                    holder.bind(dataset[position], openDetails)}
            }
            is BookViewHolderHorizontal -> {holder.bind(dataset[position])}
        }

    }

    override fun getItemCount(): Int {
        return dataset.count()
    }
}