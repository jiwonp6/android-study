package com.busanit.ch06_notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import com.busanit.ch06_notification.databinding.ActivityPendingBinding

class PendingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPendingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 1. 권한 획득
        // 권한 요청을 위한 Launcer 등록
        val permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    notification()
                } else {
                    Toast.makeText(this, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        // 버튼 클릭 시 수행
        binding.button1.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notification()
            } else {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        // 2. 채널 생성
        createNotificationChannel("ch3", "채널 3번")
        
        // 3. 알림 빌드
        // getNotificationBuilder("ch2")

        // 4. notify 메소드 실행
        // notification()
    }
   
    /* 메소드 */
    // 채널 생성 메소드
    fun createNotificationChannel(id: String, name: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // val id = "ch1"           - 코드에서 채널을 관리하기 위한 이름
            // val name = "채널 1번"     - 사용자에게 노출시킬 이름
            val importance = NotificationManager.IMPORTANCE_HIGH
            val desc = "채널에 대한 설명"

            val channel = NotificationChannel(id, name, importance).apply {
                description = desc
                enableLights(true)                            // 단말기에 LED 램프가 있다면 사용하기로 설정
                lightColor = Color.RED                              // LED 램프의 색상을 설정
                enableVibration(true)                       // 진동 사용 여부 설정
                vibrationPattern = longArrayOf(100, 200, 300, 400)  // 진동 패턴
            }
            
            // 시스템 서비스에서 알림 관리자 객체 가져오기
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            // 알림 관리자에서 채널을 등록
            manager.createNotificationChannel(channel)

        } else {
            // 26버전 이하에는 채널 필요 없음
        }
    }

    // 알림 Builder 객체 생성 메소드
    fun getNotificationBuilder(id: String): NotificationCompat.Builder {
        lateinit var builder: NotificationCompat.Builder

        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(this, id)
        } else {    // 26 버전 이하일 때는 채널 생성 X
            NotificationCompat.Builder(this)
        }
        
        return builder
    }

    // notify 메소드
    fun notification() {
        val builder = getNotificationBuilder("ch3")
        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
               .setContentTitle("알림 제목")
               .setContentText("알림 내용")

        // Intent 객체 생성: 메시지 터치 -> 다른 Activity 실행
        val intent = Intent(this@PendingActivity, TestActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        // 인텐트에 데이터를 넣어 전달
        intent.putExtra("msg", "알림을 터치하셨습니다.")
        intent.putExtra("data", "데이터")

        /* 
            pendingIntent: 알림을 클릭했을 때 애플리 케이션의 특징 Activity 실행시키는 메시지
                - FLAG_ACTIVITY_NEW_TASK: 새로운 작업 스택 생성 플래그
                - FLAG_ACTIVITY_CLEAR_TASK: 기존 작업 지우는 플래그 
                - PendingIntent.FLAG_UPDATE_CURRENT: 기존 펜딩 인텐트 업데이트 플래그
                - PendingIntent.FLAG_IMMUTABLE: 기존 펜딩 인텐트 변경 불가 플래그
            
            TaskStackBuilder: 인텐트를 스택으로 쌓는 빌더
         */
        // pendingIntent 생성
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)    // 인텐트를 스택에 추가
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }

        // 알림 클릭 시 실행할 인텐트 설정
        builder.setContentIntent(pendingIntent)

        val notification = builder.build()

        // 알림 매니저 가져오기
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // 알림 표시
        notificationManager.notify(22, notification)

    }
}