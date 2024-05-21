package com.busanit.ch09_recycler_view.addView

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch09_recycler_view.R
import com.busanit.ch09_recycler_view.databinding.ActivityAddViewBinding
import com.busanit.ch09_recycler_view.databinding.CarItemBinding

class AddViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 루트 컨테이너 뷰 바인딩 객체
        val binding = ActivityAddViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터 준비, 자동차의 목록 생성
        data class Car(val name: String, val maker: String)

        var carList = mutableListOf<Car>()
        for (i in 1 .. 20) {
            carList.add(
                Car("자동차 ${i}", "제조사 ${i}")
            )
        }

        // 레이아웃 인플레이터: XML -> 뷰 객체
        val inflater = LayoutInflater.from(this)

        carList.forEach {
            // car_item.xml 항목 하나에 대한 바인딩 객체 생성
            car
                -> val carItemBinding = CarItemBinding.inflate(inflater, binding.container, false)

                carItemBinding.run {
                    carImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_launcher_background, theme))
                    carNameTextView.text = car.name
                    carMakerTextView.text = car.maker
                }

                // 컨테이너에 항목 뷰를 추가
                binding.container.addView(carItemBinding.root)
        }
    }
}