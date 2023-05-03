package com.example.notetakinapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakinapp.model.Note
import com.example.notetakinapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(
    app: Application,
    private val noteRepository: NoteRepository
    ): AndroidViewModel(app) { // Class behave as View-model

    fun addNote(note: Note) {
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNote(query: String?) = noteRepository.searchNote(query)
}