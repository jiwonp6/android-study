package com.busanit.ch11_persistence.ex_todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch11_persistence.databinding.ActivityTodoBinding
import kotlinx.coroutines.launch

class TodoActivity : AppCompatActivity() {
    lateinit var binding: ActivityTodoBinding

    lateinit var todoAdapter: TodoAdapter

    lateinit var todoDatabase: TodoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // DB 초기화
        todoDatabase = TodoDatabase.getDatabase(this)

        // 리사이클러뷰 초기화
        todoAdapter = TodoAdapter(todoDatabase.todoDao()) // { todo -> deleteTodo(todo)}
            // (매개변수로 함수 전달), {이벤트 핸들러 전달}
        binding.recyclerView.adapter = todoAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // 데이터 불러오기
        loadTodoList()

        // '추가' 버튼 클릭
        binding.button.setOnClickListener {
            val task = binding.editText.text.toString()
            val todo = Todo(task = task)            // 객체 생성
            lifecycleScope.launch {
                todoDatabase.todoDao().insert(todo) // 데이터 삽입(Dao 위임)
                loadTodoList()                      // 데이터 새로고침
            }
            binding.editText.text.clear()           // 입력창 지우기
        }
    }

    // 데이터 불러오는 메소드
    private fun loadTodoList() {
        lifecycleScope.launch {
            val todoList = todoDatabase.todoDao().getAllTodo() // 데이터 가져오기
            todoAdapter.setTodoListData(todoList)  // 어댑터에 데이터 설정
        }
    }

    // 데이터 삭제 메소드
    private fun deleteTodo(todo: Todo) {
        lifecycleScope.launch {
            todoDatabase.todoDao().delete(todo)
            loadTodoList()
        }
    }

}