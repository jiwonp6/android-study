package com.busanit.ch06_notification

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch06_notification.databinding.ActivityRingtonBinding

class RingtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =  ActivityRingtonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 알림 소리 재생 버튼
        binding.button1.setOnClickListener {
            // 기본 알림 소리의 URI 를 가져옴
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            // applicationContext: 애플리케이션의 전역 인스턴스
            // 알람 소리를 가져오는 Ringtone 객체를 생성
            val ringtone: Ringtone = RingtoneManager.getRingtone(applicationContext, uri)
            ringtone.play() // 알람소리 재생
        }

        // 진동 재생
        binding.button2.setOnClickListener {
            // Vibrator 객체 얻기
            val vibrator = if (Build.VERSION.SDK_INT >= 31) {
                val vibratorManager =
                    getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager

                vibratorManager.defaultVibrator     // 기본 진동기 반환
            } else {
                getSystemService(VIBRATOR_SERVICE) as Vibrator
            }

            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(500, 1000, 500, 2000), intArrayOf(0, 50, 0, 200), -1))
            } else {
                // 500 밀리초 동안 진동
                vibrator.vibrate(500)
            }

        }





    }
}