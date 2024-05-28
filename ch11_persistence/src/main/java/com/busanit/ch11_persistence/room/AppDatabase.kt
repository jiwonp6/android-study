package com.busanit.ch11_persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase

// 데이터베이스를 관리하는 클래스
// DAO 인스턴스 제공
@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}