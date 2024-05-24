package com.busanit.quiz

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(val adapter: ToDoAdapter): ItemTouchHelper.Callback() {
    // 드래그와 스와이프를 위한 플래그를 설정
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        // 위 아래 방향으로 드래그 플래그 설정
        val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        // 양 옆 방향으로 스와이프 플래그 설정
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    // 드래그 했을 때 이동 동작 처리
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPosition = viewHolder.adapterPosition   // 이동 시작 위치
        val toPosition = target.adapterPosition         // 이동 끝 위치
        adapter.moveTodo(fromPosition, toPosition)      // 어댑터에서 데이터 위치 변경
        return true
    }

    // 항목이 스와이프 되었을 때 동작 (Flag 좌, 우)
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition   // 스와이프 되는 아이템 위치
        adapter.deleteTodo(position)                // 어댑터에서 데이터 삭제 및 알림
    }

}