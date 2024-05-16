package com.busanit.ch06_notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.busanit.ch06_notification.databinding.ActivityNotiBinding

class NotiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNotiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 권한 요청을 위한 Launcer 등록
        val permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    notification()
                } else {
                    Toast.makeText(this, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        binding.button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notification()
            } else {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    //
    fun notification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder
        
        if (Build.VERSION.SDK_INT >= 26) {
            // API 26 이상에서는 Channel 을 설정해야 함
            // 채널 객체 생성
            val channel = NotificationChannel("ch1", "1번 채널", NotificationManager.IMPORTANCE_LOW)
            channel.description = "1번 채널입니다."       // 채널 설명 설정

            // 알림 관리자에서 생성한 객체 채널 등록 (생성)
            notificationManager.createNotificationChannel(channel)

            // 채널 ID를 사용해서 빌더 생성
            builder = NotificationCompat.Builder(this, "ch1")
        } else {
            // 26버전 이하에서는 채널 없이 빌더 생성
            builder = NotificationCompat.Builder(this)
        }
        
        builder.apply {
            setSmallIcon(android.R.drawable.ic_notification_overlay)    // 알림 아이콘
            setContentTitle("알림 제목")
            setContentText("알림 내용")
            setWhen(System.currentTimeMillis())     // 알림 발생 시간 설정
        }

        // 알림을 표시
        notificationManager.notify(11, builder.build())
    }
}