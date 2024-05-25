package com.example.ksiazki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class BookDetail : Fragment() {

    private lateinit var bookTitleTextView: TextView
    private lateinit var bookAuthorTextView: TextView
    private lateinit var bookImageView: ImageView

    private val images = listOf(R.drawable.img1, R.drawable.img2, R.drawable.img3)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_book_detail, container, false)

        bookImageView = view.findViewById(R.id.bookImageView)
        bookTitleTextView = view.findViewById(R.id.textViewTitle)
        bookAuthorTextView = view.findViewById(R.id.textViewAuthor)

        val book = arguments?.getString("BOOK")
        book?.let {
            val bookDetails = it.split(" - ")
            bookTitleTextView.text = bookDetails[0]
            bookAuthorTextView.text= bookDetails[1]

            val randomImage = images.random()
            bookImageView.setImageResource(randomImage)

        }

        return view
    }



    companion object {
        @JvmStatic
        fun newInstance(book: String): BookDetail{
            val fragment = BookDetail()
            val args = Bundle()
            args.putString("BOOK", book)
            fragment.arguments  = args
            return fragment
        }

    }
}