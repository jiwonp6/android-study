package com.busanit.quiz

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.busanit.quiz.databinding.TodoBinding
import java.util.Collections

class ToDoAdapter (val todoList: MutableList<Todo>): RecyclerView.Adapter<ToDoAdapter.TodoViewHolder>() {
    // 5. 뷰 홀더 작성하기
    // 매개변수로 항목의 레이아웃 뷰 바인딩을 삽입
    inner class TodoViewHolder(val binding: TodoBinding) : RecyclerView.ViewHolder(binding.root) {
        // detail로 이동
        init {
            binding.textView.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                val editText = todoList[position].text
                val checkBox = todoList[position].check
                intent.putExtra("editText", editText)
                intent.putExtra("checkBox", checkBox)
                binding.root.context.startActivity(intent)
            }
        }
    }

    // 6. 어댑터의 메소드 구현 (onCreateViewHolder, getItemCount, onBindViewHolder)
    // 6-1. onCreateViewHolder: 뷰 홀더 초기화
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    // 6-2. getItemCount: 데이터 개수
    override fun getItemCount(): Int = todoList.size

    // 6-3. onBindViewHolder: 데이터와 뷰 홀더 바인딩
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.run {
            checkBox.setOnClickListener {
                if (!todoList[position].check) {
                    todoList[position].check = true
                } else {
                    todoList[position].check = false
                }
                changeTodo(position, textView.text.toString(), todoList[position].check)
            }

            textView.text = todoList[position].text
            checkBox.isChecked = todoList[position].check
        }
        
        // 알림 메시지
        holder.itemView.setOnClickListener {
            val context = holder.binding.root.context
            Toast.makeText(context, "${todoList[position]}", Toast.LENGTH_SHORT).show()
        }
    }

    // 아이템을 이동하기 위한 메소드
    fun moveTodo(fromPosition: Int, toPosition: Int) {
        // 두 아이템의 위치를 변경 (swap)
        Collections.swap(todoList, fromPosition, toPosition)
        // 어댑터에 아이템 변경 알림 (화면에 바뀐 정보로 표시되도록)
        notifyItemMoved(fromPosition, toPosition)
    }

    // 아이템 삭제 메소드
    fun deleteTodo(position: Int) {
        todoList.removeAt(position)     // 제거
        notifyItemRemoved(position)     // 알림 (화면에 바뀐 정보로 표시되도록)
    }

    // 아이템 추가 메소드
    fun addTodo(editText: String) {
        todoList.add(Todo(editText, false))
        notifyItemChanged(todoList.size)
    }
    fun changeTodo(position: Int, editText: String, check: Boolean) {
        todoList.set(position, Todo(editText, check))
        notifyItemChanged(position)
    }

}