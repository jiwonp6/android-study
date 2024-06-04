package com.busanit.ch12_network.retrofit.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch12_network.databinding.ItemPostBinding
import com.busanit.ch12_network.retrofit.activity.CommentActivity
import com.busanit.ch12_network.retrofit.model.Post

// 아이템을 RecyclerView 어댑터
class PostAdapter(val posts: List<Post>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    class PostViewHolder(var binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.titleTextView.text = post.title
            binding.bodyTextView.text = post.title
            // 항목 클릭 시 해당 댓글 액티비티 시작, 데이터 전달
            binding.root.setOnClickListener {
                // 컨텍스트 추출
                val context = it.context

                // 컨텍스트에서 id 담아서 CommentActivity 로 이동
                val intent = Intent(context, CommentActivity::class.java)
                intent.putExtra("postId", post.id)
                context.startActivity(intent)


            }
        }

    }

    // XML 레이아웃을 인플레이트하여 뷰홀더 객체의 매개변수로 넣어 뷰홀더를 생성 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding
            = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    // 전체 데이터의 사이즈
    override fun getItemCount(): Int = posts.size

    // 주어진 위치의 Post 객체를 뷰홀더에 바인딩
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }
}