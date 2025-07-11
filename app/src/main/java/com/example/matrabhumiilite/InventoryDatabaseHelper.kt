package com.example.matrabhumiilite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class InventoryDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "inventory.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE inventory (id INTEGER PRIMARY KEY, name TEXT, quantity TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS inventory")
        onCreate(db)
    }

    fun insertItem(name: String, quantity: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("quantity", quantity)
        db.insert("inventory", null, values)
        db.close()
    }

    fun getAllItems(): List<Pair<String, String>> {
        val list = mutableListOf<Pair<String, String>>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM inventory", null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(1)
                val quantity = cursor.getString(2)
                list.add(Pair(name, quantity))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }
}
