package com.busanit.quiz

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val todoList = mutableListOf<Todo>()

    // 액티비티에서 어댑터 및 레이아웃 매니저 설정
    val adapter = ToDoAdapter(todoList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 더미 데이터
        for (i in 1..15) {
            todoList.add(Todo("할 일 $i", false))
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // ItemTouchHelperCallback 을 실행하는 ItemTouchHelper 인스턴스 생성
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))

        // ItemTouchHelper 인스턴스에 recyclerView 에 연결
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        /* todo 추가 */
        // InputMethodManager 가져오기
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // todo 추가 버튼
        binding.addButton.setOnClickListener{
            val editText: String = binding.editText.text.toString()
            adapter.addTodo(editText)
            binding.editText.text.clear()
            
            // EditText의 Window Token 가져오기
            val windowToken = binding.editText.windowToken

            // 키보드 내리기
            imm.hideSoftInputFromWindow(windowToken, 0)
            
            // 추가 알림 메시지 뿌리기
            Toast.makeText(binding.root.context, "${todoList.size} 번째 할 일을 추가하였습니다.", Toast.LENGTH_SHORT).show()
        }

        binding.editText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                // 키보드 내리기
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
    }
}