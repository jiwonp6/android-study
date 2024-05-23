package com.busanit.ch09_recycler_view.itemTouch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch09_recycler_view.databinding.ActivityItemBinding

class ItemTouchActivity : AppCompatActivity() {
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

        // 액티비티에서 어댑터 및 레이아웃 매니저 설정
        val adapter = ItemAdapter(itemList)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // ItemTouchHelperCallback 을 실행하는 ItemTouchHelper 인스턴스 생성
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))

        // ItemTouchHelper 인스턴스에 recyclerView 에 연결
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

    }
}