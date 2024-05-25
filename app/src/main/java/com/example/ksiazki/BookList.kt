package com.example.ksiazki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class BookList : Fragment() {

    private lateinit var bookListView: ListView
    private lateinit var titleEditText: EditText
    private lateinit var authorEditText: EditText
    private lateinit var addButton: Button

    private val books = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)

        bookListView = view.findViewById(R.id.bookListView)
        titleEditText = view.findViewById(R.id.title)
        authorEditText = view.findViewById(R.id.author)
        addButton = view.findViewById(R.id.addButton)

        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1, books)
        bookListView.adapter = adapter

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val author = authorEditText.text.toString()

            if(title.isNotEmpty() && author.isNotEmpty()){
                val book = "$title - $author"
                books.add(book)
                adapter.notifyDataSetChanged()
                titleEditText.text.clear()
                authorEditText.text.clear()
            }
        }

        bookListView.setOnItemClickListener { _, _, position, _ ->
            val selectedBook = books[position]
            parentFragmentManager.commit {
                replace(R.id.fragment_container, BookDetail.newInstance(selectedBook))
                addToBackStack(null)
            }
        }

        return view
    }


}