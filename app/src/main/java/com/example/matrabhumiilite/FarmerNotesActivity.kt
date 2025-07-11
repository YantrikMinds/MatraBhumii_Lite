package com.example.matrabhumiilite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matrabhumiilite.adapter.NoteAdapter
import com.example.matrabhumiilite.db.NotesDatabaseHelper
import com.example.matrabhumiilite.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton



class FarmerNotesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter
    private lateinit var notesList: MutableList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_notes)

        recyclerView = findViewById(R.id.recyclerViewNotes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val db = NotesDatabaseHelper(this)
        notesList = db.getAllNotes().toMutableList()

        adapter = NoteAdapter(this, notesList)
        recyclerView.adapter = adapter

        val addNoteButton = findViewById<FloatingActionButton>(R.id.fabAddNote)
        addNoteButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val db = NotesDatabaseHelper(this)
        notesList = db.getAllNotes().toMutableList()
        adapter.updateNotes(notesList)
    }
}

