package com.example.matrabhumiilite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.matrabhumiilite.model.Note
import java.text.SimpleDateFormat
import java.util.*

class NotesDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "notes_db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE notes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                content TEXT,
                timestamp LONG,
                date TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(db)
    }

    fun insertNote(title: String, content: String): Long {
        val db = writableDatabase
        val values = ContentValues()
        val currentTimeMillis = System.currentTimeMillis()

        values.put("title", title)
        values.put("content", content)
        values.put("timestamp", currentTimeMillis)
        values.put("date", SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(currentTimeMillis)))

        return db.insert("notes", null, values)
    }

    fun getAllNotes(): List<Note> {
        val notes = mutableListOf<Note>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM notes ORDER BY id DESC", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
                val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
                val timestamp = cursor.getLong(cursor.getColumnIndexOrThrow("timestamp"))
                val date = cursor.getString(cursor.getColumnIndexOrThrow("date"))

                val note = Note(id, title, content, timestamp, date)
                notes.add(note)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return notes
    }
    fun updateNote(id: Int, title: String, content: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("content", content)
            put("date", getCurrentDateTime())
        }
        return db.update("notes", values, "id = ?", arrayOf(id.toString()))
    }

    fun deleteNote(id: Int): Int {
        val db = writableDatabase
        return db.delete("notes", "id = ?", arrayOf(id.toString()))
    }
    private fun getCurrentDateTime(): String {
        val sdf = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault())
        return sdf.format(java.util.Date())
    }


}
