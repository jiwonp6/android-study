package com.busanit.ch11_persistence.ex_todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// 1. Entity 생성
@Entity(tableName = "todo_tbl")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "task")
    val task: String
)
