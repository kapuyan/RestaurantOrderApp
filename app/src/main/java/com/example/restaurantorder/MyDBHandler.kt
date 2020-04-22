package com.example.restaurantorder

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHandler(context: Context, name: String?,
                  factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 3
        private val DATABASE_NAME = "RestaurantClientDB.db"
        val TABLE_WORDS = "words_table"
        val COLUMN_ID = "_id"
        val COLUMN_WORD = "word"
        //val COLUMN_NAME = "name"
        val Name ="Name"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_WORDS_TABLE = ("CREATE TABLE $TABLE_WORDS ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_WORD TEXT, $Name TEXT);")

        db.execSQL(CREATE_WORDS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_WORDS")
        onCreate(db)
    }

    fun addOrder(word: Order) {
        val values = ContentValues()
        values.put(COLUMN_WORD, word.client)
        values.put(Name, word.dish)

        val db = this.writableDatabase

        db.insert(TABLE_WORDS, null, values)
        db.close()
    }

    fun getAllWords(): MutableList<Order> {
        val query =
            "SELECT * FROM $TABLE_WORDS"

        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null )

        val words: MutableList<Order> = mutableListOf()

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                words.add(Order(cursor.getString(1),(cursor.getString(2))))
                cursor.moveToNext()
            }
        }
        db.close()
        return words
    }

}