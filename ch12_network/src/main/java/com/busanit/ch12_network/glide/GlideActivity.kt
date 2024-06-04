package com.busanit.ch12_network.glide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.busanit.ch12_network.R
import com.busanit.ch12_network.databinding.ActivityGlideBinding

class GlideActivity : AppCompatActivity() {
    lateinit var binding: ActivityGlideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGlideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrl = "https://loremflickr.com/600/400"

        // Glide 사용해서 현재 Activity 에 이미지 로드
        Glide.with(this)
            .load(imageUrl)
            .into(binding.imageView1)

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.img)     // 이미지 로드 중일 때 보여지는 이미지
            .error(R.drawable.error_img)     // 이미지 로드 오류 시 보여지는 이미지
            .circleCrop()                    // 이미지 원형으로 변형
            .centerCrop()                    // 중간에서 자르기
            .fitCenter()                     // 가운데서 맞추기
            .override(100, 100) // 이미지 크기 조정
            .into(binding.imageView2)

        // GIF 이미지 로드
        Glide.with(this)
            .asGif()
            .load("https://media.giphy.com/media/aFTt8wvDtqKCQ/giphy.gif")
            .into(binding.imageView3)

    }
}