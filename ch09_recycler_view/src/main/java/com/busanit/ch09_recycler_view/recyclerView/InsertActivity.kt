package com.busanit.ch09_recycler_view.recyclerView

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.busanit.ch09_recycler_view.databinding.ActivityInsertBinding
import com.busanit.ch09_recycler_view.databinding.StudentItemBinding

class InsertActivity : AppCompatActivity() {
    // 1. 데이터 클래스(모델) 정의
    data class Student(var name: String, var age: Int, var grade: Int)

    val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        // 2. 레이아웃 설정
        super.onCreate(savedInstanceState)
        val binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            // 어댑터와 레이아웃 매니저 설정
            recyclerView.adapter = MyRecyclerAdapter()
            recyclerView.layoutManager = LinearLayoutManager(this@InsertActivity)

            // 구분선 추가 (선택사항)
            recyclerView.addItemDecoration(DividerItemDecoration(this@InsertActivity, DividerItemDecoration.VERTICAL))
            
            // 추가 버튼이 클릭되었을 때
            addButton.setOnClickListener {
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toInt()
                val grade = editTextGrade.text.toString().toInt()

                // 입력된 값으로 객체 생성
                val student = Student(name, age, grade)
                
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

    // 3. 뷰 홀더와 어댑터 정의
    inner class MyRecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> () {
        // 뷰 홀더 정의
        inner class MyViewHolder(itemView: StudentItemBinding): RecyclerView.ViewHolder(itemView.root) {
            // 각 View(TextView)의 주소 값을 담을 변수
            var textViewName: TextView
            var textViewAge: TextView
            var textViewGrade: TextView
            init {
                // 뷰 홀더가 생성될 때 뷰 바인딩을 통해 초기화
                textViewName = itemView.textViewName
                textViewAge = itemView.textViewAge
                textViewGrade = itemView.textViewGrade
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            // 뷰 홀더에 item 레이아웃을 인플레이트하여 리턴
            val itemBinding = StudentItemBinding.inflate(layoutInflater, parent, false)
            return MyViewHolder(itemBinding)
        }

        // 항목 개수
        override fun getItemCount(): Int = studentList.size

        // 뷰 홀더와 데이터를 바인딩
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            // n번째 데이터에서 구조분해 할당으로 값 추출
            val (name, age, grade) = studentList[position]
            
            // 뷰 홀더에 데이터 바인딩
            (holder as MyViewHolder).run {
                textViewName.text = name
                textViewAge.text = "${age}세"
                textViewGrade.text = "${grade}점"
            }
        }

    }
}