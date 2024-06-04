package com.busanit.ch12_network.retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch12_network.databinding.ItemCommentBinding
import com.busanit.ch12_network.retrofit.model.Comment

// 아이템을 RecyclerView 어댑터
class CommentAdapter(val comments: List<Comment>): RecyclerView.Adapter<CommentAdapter.ItemViewHolder>() {
    class ItemViewHolder(var binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.nameTextView.text = comment.name
            binding.emailTextView.text = comment.email
            binding.bodyTextView.text = comment.body
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding
                = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(comments[position])
    }

}