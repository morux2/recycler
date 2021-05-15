package com.example.recycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CodeNameAdapter(val codenames: List<String>) : RecyclerView.Adapter<VH>() {
    // 表示内容の生成 https://maku77.github.io/android/ui/recycler-view.html
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.code_name_item, parent, false)
        return VH(itemView)
    }

    // 表示内容の入れ替え https://maku77.github.io/android/ui/recycler-view.html
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(codenames[position])
    }

    override fun getItemCount(): Int {
        return codenames.size
    }
}

/*
    https://dogwood008.github.io/kotlin-web-site-ja/docs/reference/classes.html
    クラスヘッダ内のコロンの後に型を書くと、明示的にスーパータイプを宣言できます
    もしこのような（明示的にスーパータイプを宣言する）クラスがプライマリコンストラクタをもつなら、基底の型をプライマリコンストラクタの引数を使用して、そこで初期化できる
    （し、しなければいけません）。
*/
class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val codeNameText: TextView = itemView.findViewById(R.id.code_name_txt)

    fun bind(codename: String) {
        codeNameText.text = codename
    }
}