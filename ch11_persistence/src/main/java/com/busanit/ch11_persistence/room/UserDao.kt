package com.busanit.ch11_persistence.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// 데이터 접근 객체의 인터페이스
// 데이터 베이스 작업을 정의
@Dao
interface UserDao {
    // Insert 문 Object - Relational Mapping: ORM
    @Insert
    suspend fun insert(user: User)  // 데이터 삽입

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>
    
    // suspend 키워드: 해당 함수가 코틀린 코루틴에서 실행됨을 나타냄
    // 코루틴: UI 작업을 하는 메인 스레드가 멈추지 않도록 DB I/O를 비동기적으로 실행할 수 있도록 함
}