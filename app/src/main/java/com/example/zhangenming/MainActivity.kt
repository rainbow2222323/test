package com.example.zhangenming

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

/**
 * 第一个 Activity：显示按价格从低到高排序后的书籍列表，
 * 单击某一本书后启动第二个 Activity。
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_AUTHOR = "extra_author"
        const val EXTRA_PUBLISHER = "extra_publisher"
        const val EXTRA_PRICE = "extra_price"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 设置标题栏，标题为“姓名 + 学号”
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = getString(R.string.student_title)

        // 在代码中新建多个书籍对象并放入一个数组
        val books: Array<Book> = arrayOf(
            Book("第一行代码", "郭霖", "人民邮电出版社", 79.0),
            Book("Android开发艺术探索", "任玉刚", "电子工业出版社", 79.0),
            Book("Java编程思想", "Bruce Eckel", "机械工业出版社", 108.0),
            Book("算法导论", "Thomas H. Cormen", "机械工业出版社", 128.0),
            Book("代码大全", "Steve McConnell", "电子工业出版社", 128.0),
            Book("深入理解计算机系统", "Randal E. Bryant", "机械工业出版社", 139.0),
            Book("数据结构与算法分析", "Mark Allen Weiss", "机械工业出版社", 69.0)
        )

        // 对数组里的书籍按价格从低到高排序
        books.sortBy { it.price }

        // 采用列表显示这些书籍
        val listView = findViewById<ListView>(R.id.book_list_view)
        listView.adapter = BookAdapter(this, books)

        // 单击某一本书，启动第二个 Activity 并传递书籍信息
        listView.setOnItemClickListener { _, _, position, _ ->
            val book = books[position]
            val intent = Intent(this, BookDetailActivity::class.java).apply {
                putExtra(EXTRA_TITLE, book.title)
                putExtra(EXTRA_AUTHOR, book.author)
                putExtra(EXTRA_PUBLISHER, book.publisher)
                putExtra(EXTRA_PRICE, book.price)
            }
            startActivity(intent)
        }

        // 处理系统栏，避免内容被状态栏/导航栏遮挡
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
