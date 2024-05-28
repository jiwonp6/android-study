package com.busanit.ch11_persistence.external

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.busanit.ch11_persistence.databinding.ActivityExternalBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

class ExternalActivity : AppCompatActivity() {
    lateinit var binding: ActivityExternalBinding

    private val REQUEST_CODE = 10

    // 외부 저장소 (External Stroage)
    // 1. 앱 전용 외부 저장소 : 앱이 삭제되면 데이터 함께 삭제 (SD카드 등에 저장 가능) (앱 전용 공간)
    // 2. 공용 외부 저장소 : 앱이 삭제되어도 삭제되지 않음 (디바이스 저장소)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExternalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 앱의 외부 저장소에 파일 저장, 다른 앱에서도 접근 가능
        // 사진, 문서, 용량 등의 대용량 데이터를 앱 내부 저장소 외 별개의 공간에 저장
        // 외부 저장소에 대한 권한이 필요

        // 1. AndroidManifest.xml 에 권한 추가
        // <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
        // <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

        // 권한이 부여되지 않은 경우
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
        val permissionRequest = mutableListOf<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionRequest.add(permission)
            }
        }

        // 권한 요청
        if (permissionRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionRequest.toTypedArray(), REQUEST_CODE)
        }

        val fileName = "external_storage.txt"

        binding.run {
            buttonSave.setOnClickListener {
                // 파일을 데이터에 저장
                val inputData = editTextName.text.toString()
                saveToFile(fileName, inputData)
            }

            buttonLoad.setOnClickListener{
                // 파일에서 데이터를 불러오는 함수
                val readData = readFromFile(fileName)
                textView.text = readData
            }

            buttonClear.setOnClickListener {
                deleteExternalFile(fileName)
                textView.text = "파일이 삭제되었습니다."
            }
        }
    }

    // 권한 요청 결과
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한 부여 (Pass)
            } else {
                binding.textView.text = "외부 저장소 권한이 거부되었습니다."
            }
        }
    }

    // 외부 파일 삭제 메소드
    private fun deleteExternalFile(fileName: String) {
        try {
            // 공용 저장소 파일 경로
            val publicPath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

            // 파일 경로가 존재하면 삭제
            if (publicPath != null) {
                val file = File(publicPath, fileName)
                file.delete()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    // 파일 읽기 메소드
    private fun readFromFile(fileName: String): String? {
        try {
            // 공용저장소 경로
            val publicPath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            
            // 파일 객체 생성
            val file = File(publicPath, fileName)

            // 입력 스트림 열기
            val fileInputStream = FileInputStream(file)
            val isr = InputStreamReader(fileInputStream)
            val br = BufferedReader(isr)
            val sb = StringBuilder()
            var text: String?

            while (br.readLine().also {             // 파일 끝까지 한 줄씩 읽기
                    text = it
                }!=null) {
                sb.append(text)                             // 빌더에 추가
            }

            // 리소스 정리
            br.close()
            isr.close()
            fileInputStream.close()

            // 빌더에 담은 문자열 반환
            val readData = sb.toString()
            return readData

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return null
        }
    }

    // 파일 쓰기 메소드
    private fun saveToFile(fileName: String, inputData: String) {
        // 외부 저장소가 쓰기 가능한지 확인
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            // 외부 저장소 파일 경로 지정
            val externalPath    // 앱 전용 저장소
                = getExternalFilesDir(null)
            val publicPath      // 공용 외부 저장소
                = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

            // 타입
            Environment.DIRECTORY_PICTURES  // 사진 저장
            Environment.DIRECTORY_MOVIES    // 동영상 저장
            Environment.DIRECTORY_DOCUMENTS // 문서 저장
            Environment.DIRECTORY_DOWNLOADS // 다운로드 폴더

            // 파일 객체 생성
            val file = File(externalPath, fileName)

            // 파일 출력 스트림 쓰기
            try {
                val fos = FileOutputStream(fileName)
                fos.write(inputData.toByteArray())
                fos.close()

            } catch (e: IOException) {
                e.printStackTrace()     // 예외 발생 시 출력
            }
        }
    }
}