package com.busanit.quiz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    // 표시할 프래그먼트를 담을 리스트
    val fragmentList = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 더미 데이터
        val todoList = mutableListOf<Todo>()
        for (i in 1..15) {
            todoList.add(Todo("할 일 $i", false))
        }


        // 액티비티에서 어댑터 및 레이아웃 매니저 설정
        val adapter = ToDoAdapter(todoList)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // ItemTouchHelperCallback 을 실행하는 ItemTouchHelper 인스턴스 생성
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))

        // ItemTouchHelper 인스턴스에 recyclerView 에 연결
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.addButton.setOnClickListener{
            val editText: String = binding.editText.text.toString()
            adapter.addTodo(editText)
            binding.editText.text.clear()
            Toast.makeText(binding.root.context, "${todoList.size} 번째 할 일을 추가하였습니다.", Toast.LENGTH_SHORT).show()
        }

    }
}