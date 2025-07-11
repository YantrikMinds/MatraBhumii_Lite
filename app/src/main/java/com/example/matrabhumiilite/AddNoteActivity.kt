package com.example.matrabhumiilite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.matrabhumiilite.db.NotesDatabaseHelper

class AddNoteActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var editContent: EditText
    private lateinit var saveButton: Button

    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTitle = findViewById(R.id.editNoteTitle)
        editContent = findViewById(R.id.editNoteContent)
        saveButton = findViewById(R.id.btnSaveNote)

        val db = NotesDatabaseHelper(this)

        // Check if editing existing note
        val intent = intent
        if (intent.hasExtra("note_id")) {
            noteId = intent.getIntExtra("note_id", -1)
            editTitle.setText(intent.getStringExtra("note_title"))
            editContent.setText(intent.getStringExtra("note_content"))
        }

        saveButton.setOnClickListener {
            val title = editTitle.text.toString().trim()
            val content = editContent.text.toString().trim()

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Title or content can't be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (noteId != -1) {
                // Update existing note
                db.updateNote(noteId, title, content)
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()
            } else {
                // Add new note
                db.insertNote(title, content)
                Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
            }

            finish() // go back to notes list
        }
    }
}
