package com.busanit.ch11_persistence.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entitiy 클래스: 데이터 베이스의 테이블을 나타내는 클래스
// users 라는 이름의 테이블
@Entity(tableName = "users")
data class User(
    // 기본키, 자동생성
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "age")
    val age: Int

    /*
        // 프로퍼티의 이름과 컬럼의 이름이 다른 경우
        ,@ColumnInfo(name = "first_name")
        val firstName: String
     */
)
// 데이터 클래스의 프로퍼티는 테이블의 컬럼을 가리킴
// 테이블의 컬럼명은 @ColumnInfo 의 name 속성으로 저장
