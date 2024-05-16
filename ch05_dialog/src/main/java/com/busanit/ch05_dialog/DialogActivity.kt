package com.busanit.ch05_dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch05_dialog.databinding.ActivityDialogBinding
import com.busanit.ch05_dialog.databinding.CustomDialogBinding

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityDialogBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 경고 다이얼로그
        binding.button1.setOnClickListener {
            // 다이얼로그를 생성하기 위한 빌더 객체 생성
            val builder = AlertDialog.Builder(this)
            builder.apply {
                setTitle("경고")                          // 타이틀
                setMessage("작업을 수행하시겠습니까?")       // 메시지
                setIcon(R.mipmap.ic_launcher)            // 아이콘

                // 사용자 응답별 수행할 작업(긍정, 부정)
                setPositiveButton("예") {
                    dialog, which -> binding.textView.text = "[예] 버튼을 눌렀습니다."
                }
                setNegativeButton("아니오") {
                    dialog, which -> binding.textView.text = "[아니오] 버튼을 눌렀습니다."
                }
            }
            builder.show()
        }

        // 날짜 선택 다이얼로그
        binding.button2.setOnClickListener {
            // 사용자에게 기본값으로 보여줄 연/월/일
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // 사용자가 날짜를 선택했을 때 수행할 작업
            val listener = object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    // 사용자로부터 입력받은 연, 월, 일
                    binding.textView.text = "${year}년 ${month + 1}월 ${dayOfMonth}일"
                }
            }
            
            // 다이얼로그 객체 생성
            val datePickerDialog = DatePickerDialog(this, listener, year, month, day)
            datePickerDialog.show()
        }
        
        // 시간 선택 다이얼로그
        binding.button3.setOnClickListener { 
            // 사용자에게 보여줄 기본 시간
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)

            // 다이얼로그 객체 생성, (이벤트 리스너), 띄우기
            val timePickerDialog = TimePickerDialog(this, { _, hour, minute ->
                binding.textView.text = "선택된 시간: ${hour}:${minute}"
            }, hour, minute, true)
            timePickerDialog.show()
        }

        // 목록 출력 다이얼로그
        val items = arrayOf<String>("사과", "바나나", "당근", "수박", "복숭아")
        binding.button4.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("항목 출력")
                setItems(items, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        binding.textView.text = "선택한 과일: ${items[which]}"
                    }
                })
                setPositiveButton("확인", null)
                show()
            }
        }

        // 체크 박스 다이얼로그
        binding.button5.setOnClickListener { 
            AlertDialog.Builder(this).run { 
                setTitle("체크박스로 선택")
                setMultiChoiceItems(items, booleanArrayOf(false, false, false, false, false), object : DialogInterface.OnMultiChoiceClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                        binding.textView.text = "${items[which]}이/가 ${if (isChecked) "선택되었습니다." else "선택해제되었습니다."}"
                    }

                })
                setPositiveButton("확인", null)
                show()
            }
        }

        // 라디오 버튼 다이얼로그
        binding.button6.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("라디오 버튼 선택")
                setSingleChoiceItems(items, 3) {
                    dialog, which -> binding.textView.text = "${items[which]}이/가 선택되었습니다."
                }
                setPositiveButton("확인", null)
                show()
            }
        }

        // 커스텀 다이얼로그
        binding.button7.setOnClickListener {
            // 뷰바인딩을 사용해서 custom_dialog.xml 레이아웃을 인플레이트
            val dialogBinding = CustomDialogBinding.inflate(layoutInflater)

            val builder = AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                create()
            }

            dialogBinding.button.setOnClickListener {
                val inputText = dialogBinding.editText.text.toString()
                binding.textView.text = "입력받은 이름 : ${inputText}"
                builder.dismiss()
            }

            builder.show()
        }
    }
}