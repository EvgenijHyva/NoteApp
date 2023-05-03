package com.example.notetakinapp.repository

import com.example.notetakinapp.database.NoteDatabase
import com.example.notetakinapp.model.Note

class NoteRepository(
    private val db: NoteDatabase
    ) {

    private val noteDB =  db.getNoteDao()
    suspend fun insertNote(note: Note) = noteDB.insertNote(note)
    suspend fun deleteNote(note: Note) = noteDB.deleteNote(note)
    suspend fun updateNote(note: Note) = noteDB.updateNote(note)

    fun getAllNotes() = noteDB.getAllNotes()
    fun searchNote(query: String?) = noteDB.searchNote(query)
}