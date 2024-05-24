package com.busanit.quiz2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.quiz2.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        // 샘플 데이터
        val itemList = mutableListOf<Item>()
        for (i in 1..100) {
            itemList.add(Item("항목 $i"))
        }

        // 어댑터 설정
        binding.recyclerView.adapter = ListAdapter(itemList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}