package com.busanit.ch09_recycler_view.itemTouch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.databinding.ItemBinding
import java.util.Collections

class ItemAdapter(val itemList: MutableList<Item>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    // 5. 뷰 홀더 작성하기
    // 매개변수로 항목의 레이아웃 뷰 바인딩을 삽입
    inner class ItemViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    // 6. 어댑터의 메소드 구현 (onCreateViewHolder, getItemCount, onBindViewHolder)
    // 6-1. onCreateViewHolder: 뷰 홀더 초기화
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    // 6-2. getItemCount: 데이터 개수
    override fun getItemCount(): Int = itemList.size

    // 6-3. onBindViewHolder: 데이터와 뷰 홀더 바인딩
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.run {
            textView.text = itemList[position].text
        }
    }
    
    // 아이템을 이동하기 위한 메소드
    fun moveItem(fromPosition: Int, toPosition: Int) {
        // 두 아이템의 위치를 변경 (swap)
        Collections.swap(itemList, fromPosition, toPosition)
        // 어댑터에 아이템 변경 알림 (화면에 바뀐 정보로 표시되도록)
        notifyItemMoved(fromPosition, toPosition)

    }

    // 아이템 삭제 메소드
    fun deleteItem(position: Int) {
        itemList.removeAt(position)     // 제거
        notifyItemRemoved(position)     // 알림 (화면에 바뀐 정보로 표시되도록)
    }
}