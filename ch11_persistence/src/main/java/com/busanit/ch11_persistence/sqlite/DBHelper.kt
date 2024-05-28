package com.busanit.ch11_persistence.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// SQLite: 경량의 관계형 데이터 베이스, 로컬 데이터 베이스
// SQLiteOpenHelper 상속 (데이터베이스 생성 및 관리 클래스)
class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // 상수
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1
    }

    // DB 생성 시 호출되는 메서드
    override fun onCreate(db: SQLiteDatabase?) {
        // 테이블 생성문
        val sql = """
            CREATE TABLE users(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                age INTEGER
            )
        """.trimIndent()
        
        // SQL 문 실행
        db?.execSQL(sql)
    }

    // DB 업그레이드 시 호출되는 메서드
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 기존 테이블 제거
        val sql = "DROP TABLE IF EXISTS users"
        db?.execSQL(sql)
        
        // 새로운 테이블 생성
        onCreate(db)
    }
}