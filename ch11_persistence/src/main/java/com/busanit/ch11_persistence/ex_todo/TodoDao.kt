package com.busanit.ch11_persistence.ex_todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

// 2. Dao 인터페이스
@Dao
interface TodoDao {
    @Insert
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM todo_tbl")
    suspend fun getAllTodo(): List<Todo>

}