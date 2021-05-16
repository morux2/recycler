package com.example.recycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // バインディングクラスの名前は、XML ファイルの名前をキャメルケースに変換して、末尾に「Binding」という単語を追加することで生成されます
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val codenames = listOf(
            Header(),
            "cupcake",
            "donut",
            "eclair",
            "froyo ",
            "gingerbread",
            "honeycomb",
            "icecreamsandwich",
            "jellybean",
            "kitkat",
            "lollipop",
            "marshmallow",
            "nougat",
            "oreo",
            "pie",
        )
        binding.recyclerView.adapter = CodeNameAdapter(codenames)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}