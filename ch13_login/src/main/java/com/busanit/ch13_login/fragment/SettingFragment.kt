package com.busanit.ch13_login.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.busanit.ch13_login.activity.TitleActivity
import com.busanit.ch13_login.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 로그아웃 버튼 클릭 -> 로그아웃
        binding.buttonLogout.setOnClickListener { logout() }
    }

    // 로그아웃 함수
    private fun logout() {
        // 1. SharedPreferences 에서 토큰, 사용자 정보 삭제
        val sharedPreferences =
            activity?.getSharedPreferences("app_pref", Context.MODE_PRIVATE)
        sharedPreferences?.edit()
            ?.remove("token")
            ?.remove("username")
            ?.apply()

        // 2. TitleActivity 로 돌려 보내고 현재 화면 종료
        startActivity(Intent(activity, TitleActivity::class.java))
        activity?.finish()
    }
}