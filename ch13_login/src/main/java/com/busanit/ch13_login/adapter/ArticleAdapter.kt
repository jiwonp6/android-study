package com.busanit.ch13_login.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch13_login.databinding.ItemArticleBinding
import com.busanit.ch13_login.model.Article

class ArticleAdapter(
    var articles: List<Article>,
    val onEdit: (Article) -> Unit,  // 수정 이벤트 핸들러
    val onDelete: (Long) -> Unit    // 삭제 이벤트 핸들러
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            // 텍스트 뷰에 데이터 바인딩
            binding.textViewTitle.text = article.title
            binding.textViewContent.text = article.content
            binding.textViewAuthor.text = article.author

            // 버튼 클릭리스너에 이벤트 핸들러 설정
            binding.buttonEdit.setOnClickListener {
                onEdit(article)
            }

            binding.buttonDelete.setOnClickListener {
                onDelete(article.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    // 게시글 목록 갱신
    fun updateArticles(newArticles: List<Article>) {
        // 기존 데이터를 새 데이터로 변경
        articles = newArticles
        notifyDataSetChanged()
    }
}