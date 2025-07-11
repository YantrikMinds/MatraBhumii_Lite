package com.example.matrabhumiilite.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.matrabhumiilite.AddNoteActivity
import com.example.matrabhumiilite.R
import com.example.matrabhumiilite.db.NotesDatabaseHelper
import com.example.matrabhumiilite.model.Note

class NoteAdapter(private val context: Context, private var notes: MutableList<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.noteTitle)
        val content: TextView = itemView.findViewById(R.id.noteContent)
        val date: TextView = itemView.findViewById(R.id.noteDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.content.text = note.content
        holder.date.text = note.date

        // 🟢 Single tap to Edit
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddNoteActivity::class.java)
            intent.putExtra("note_id", note.id)
            intent.putExtra("note_title", note.title)
            intent.putExtra("note_content", note.content)
            context.startActivity(intent)
        }

        // 🔴 Long press to Delete
        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete Note")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Delete") { _, _ ->
                    val db = NotesDatabaseHelper(context)
                    db.deleteNote(note.id)
                    notes.removeAt(position)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("Cancel", null)
                .show()
            true
        }
    }

    override fun getItemCount(): Int = notes.size

    fun updateNotes(newNotes: MutableList<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}

