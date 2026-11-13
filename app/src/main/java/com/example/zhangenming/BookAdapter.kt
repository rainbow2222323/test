package com.example.zhangenming

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.Locale

/**
 * 书籍列表适配器：把数组中的每一本书显示到列表里。
 */
class BookAdapter(context: Context, books: Array<Book>) :
    ArrayAdapter<Book>(context, 0, books.toList()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_book, parent, false)

        val book: Book = getItem(position) ?: return view

        view.findViewById<TextView>(R.id.item_title).text = book.title
        view.findViewById<TextView>(R.id.item_author).text = "作者：${book.author}"
        view.findViewById<TextView>(R.id.item_price).text =
            String.format(Locale.CHINA, "¥%.2f", book.price)

        return view
    }
}
