package com.busanit.ch09_recycler_view.recyclerView

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.databinding.ActivityDeleteBinding
import com.busanit.ch09_recycler_view.databinding.StudentItem2Binding

/*
    [연습문제]
    InsertActivity의 리사이클러뷰에서 각 아이템마다 `삭제` 버튼을 추가합니다.
    아이템의 `삭제`버튼이 클릭될 때 해당 아이템이 리사이클러뷰에서 삭제되도록 구현하세요.
 */
class DeleteActivity : AppCompatActivity() {
    data class Student(var name: String, var age: Int, var grade: Int)
    val studentList = mutableListOf<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.run {
            recyclerView.adapter = MyRecyclerAdapter()
            recyclerView.layoutManager = LinearLayoutManager(this@DeleteActivity)

            recyclerView.addItemDecoration(DividerItemDecoration(this@DeleteActivity, DividerItemDecoration.VERTICAL))

            // 추가 버튼이 클릭되었을 때
            addButton.setOnClickListener {
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toInt()
                val grade = editTextGrade.text.toString().toInt()

                // 입력된 값으로 객체 생성
                val student = DeleteActivity.Student(name, age, grade)

                // 데이터 리스트에 추가
                studentList.add(student)

                // 입력 필드를 초기화
                editTextName.setText("")
                editTextAge.setText("")
                editTextGrade.setText("")

                // 어댑터에 데이터 변경사실 알림
                recyclerView.adapter?.notifyDataSetChanged()

                // 특정 위치 데이터 삽입 알림
                // recyclerView.adapter?.notifyItemInserted(studentList.size-1)

                // 특적위치 데이터 제거 알림
                // val position = 1
                // recyclerView.adapter?.notifyItemRemoved(position)
            }

        }
    }

    inner class MyRecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> () {
        inner class MyViewHolder(itemView: StudentItem2Binding): RecyclerView.ViewHolder(itemView.root) {
            // 각 View(TextView)의 주소 값을 담을 변수
            var textViewName: TextView
            var textViewAge: TextView
            var textViewGrade: TextView
            init {
                textViewName = itemView.textViewName
                textViewAge = itemView.textViewAge
                textViewGrade = itemView.textViewGrade

                itemView.deleteButton.setOnClickListener {
                    studentList.removeAt(adapterPosition)
                    Log.d("mylog", "onBindViewHolder: ${adapterPosition}")
                    notifyDataSetChanged()
                }
            }

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemBinding = StudentItem2Binding.inflate(layoutInflater, parent, false)
            return MyViewHolder(itemBinding)
        }

        override fun getItemCount(): Int = studentList.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val (name, age, grade) = studentList[position]
            val itemBinding = StudentItem2Binding.inflate(layoutInflater)
            (holder as DeleteActivity.MyRecyclerAdapter.MyViewHolder).run {
                textViewName.text = name
                textViewAge.text = "${age}세"
                textViewGrade.text = "${grade}점"
            }

        }

    }
}