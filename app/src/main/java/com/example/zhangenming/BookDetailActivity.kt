package com.example.zhangenming

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import java.util.Locale

/**
 * 第二个 Activity：显示被选中书籍的完整信息。
 */
class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_detail)

        // 设置标题栏并显示返回按钮
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = getString(R.string.detail_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 读取第一个 Activity 传递过来的书籍信息
        val bookTitle = intent.getStringExtra(MainActivity.EXTRA_TITLE) ?: ""
        val author = intent.getStringExtra(MainActivity.EXTRA_AUTHOR) ?: ""
        val publisher = intent.getStringExtra(MainActivity.EXTRA_PUBLISHER) ?: ""
        val price = intent.getDoubleExtra(MainActivity.EXTRA_PRICE, 0.0)

        // 显示被选中书籍的完整信息
        findViewById<TextView>(R.id.detail_title).text = bookTitle
        findViewById<TextView>(R.id.detail_author).text = "作者：$author"
        findViewById<TextView>(R.id.detail_publisher).text = "出版社：$publisher"
        findViewById<TextView>(R.id.detail_price).text =
            String.format(Locale.CHINA, "价格：¥%.2f", price)

        // 处理系统栏，避免内容被状态栏/导航栏遮挡
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
