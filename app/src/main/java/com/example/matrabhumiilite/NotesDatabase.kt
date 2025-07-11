package com.example.matrabhumiilite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Note(val id: Int, val title: String, val content: String, val date: String)

class NotesDatabase(context: Context) :
    SQLiteOpenHelper(context, "notes.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE notes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                content TEXT,
                date TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(db)
    }

    fun addNote(title: String, content: String, date: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("title", title)
        values.put("content", content)
        values.put("date", date)
        db.insert("notes", null, values)
        db.close()
    }

    fun getAllNotes(): List<Note> {
        val notes = mutableListOf<Note>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM notes ORDER BY id DESC", null)
        while (cursor.moveToNext()) {
            val note = Note(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )
            notes.add(note)
        }
        cursor.close()
        db.close()
        return notes
    }
}
