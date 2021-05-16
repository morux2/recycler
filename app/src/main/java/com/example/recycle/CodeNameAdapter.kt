package com.example.recycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// 参考 https://medium.com/@ivancse.58/android-and-kotlin-recyclerview-with-multiple-view-types-65285a254393
// TODO * in out Any Nothing を理解

class CodeNameAdapter(val items: List<Any>) : RecyclerView.Adapter<BaseVH<Any>>() {
    // 表示内容の生成 https://maku77.github.io/android/ui/recycler-view.html

    /*
        https://qiita.com/tkhs0604/items/261e94a42b7097dfd204
        クラス内に生成されるシングルトン
    */
    companion object {
        private const val TYPE_CODENAME_TXT = 0
        private const val TYPE_HEADER_BUTTON = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<Any> {
        return when (viewType) {
            TYPE_CODENAME_TXT -> CodeNameVH(
                LayoutInflater.from(parent.context).inflate(R.layout.code_name_item, parent, false)
            )
            TYPE_HEADER_BUTTON -> HeaderButtonVH(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.header_button_item, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid type of data")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = items[position]
        return when (comparable) {
            is Header -> TYPE_HEADER_BUTTON
            is String -> TYPE_CODENAME_TXT
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }


    // 表示内容の入れ替え https://maku77.github.io/android/ui/recycler-view.html
    override fun onBindViewHolder(holder: BaseVH<Any>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

/*
    https://dogwood008.github.io/kotlin-web-site-ja/docs/reference/classes.html
    クラスヘッダ内のコロンの後に型を書くと、明示的にスーパータイプを宣言できます
    もしこのような（明示的にスーパータイプを宣言する）クラスがプライマリコンストラクタをもつなら、基底の型をプライマリコンストラクタの引数を使用して、そこで初期化できる
    （し、しなければいけません）。
*/
class HeaderButtonVH(itemView: View) : BaseVH<Any>(itemView) {
    val buttonTxt: String = "Changed!"
    val headerButton: Button = itemView.findViewById(R.id.header_button)
    override fun bind(item: Any) {
        headerButton.text = buttonTxt
        headerButton.setOnClickListener {
            Toast.makeText(it.context, "Clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}

class CodeNameVH(itemView: View) : BaseVH<Any>(itemView) {
    val codeNameText: TextView = itemView.findViewById(R.id.code_name_txt)
    override fun bind(item: Any) {
        codeNameText.text = item as String
    }
}

// Kotlinのクラスは型パラメータを持てる
abstract class BaseVH<Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Any)
}

// TODO もっといい書き方
class Header