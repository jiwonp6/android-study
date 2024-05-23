package com.busanit.ch09_recycler_view.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.databinding.ActivityItemBinding
import com.busanit.ch09_recycler_view.databinding.ItemBinding

class ItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 더미 데이터
        val itemList = mutableListOf<Item>()
        for (i in 1..100) {
            itemList.add(Item("항목 $i"))
        }

        // 7. 액티비티에서 어댑터 및 레이아웃 매니저 설정
        binding.recyclerView.adapter = ItemAdapter(itemList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }
}

// 1. 데이터 클래스 생성
data class Item(val text: String)

// 2. 리사이클러뷰 레이아웃 XML 설정
// 3. 아이템 항목 레이아웃 XML 설정

// 4. 어댑터 클래스 작성하기
// 매개변수로 데이터 리스트를 받음 (기타 매개변수 추가 가능)
// RecyclerView.Adapter 상속받고, 커스텀 뷰 홀더를 제네릭으로 추가
class ItemAdapter(val itemList: MutableList<Item>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    // 5. 뷰 홀더 작성하기
    // 매개변수로 항목의 레이아웃 뷰 바인딩을 삽입
    inner class ItemViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {

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
}