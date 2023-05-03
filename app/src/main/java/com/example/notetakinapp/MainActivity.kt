package com.example.notetakinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notetakinapp.database.NoteDatabase
import com.example.notetakinapp.databinding.ActivityMainBinding
import com.example.notetakinapp.repository.NoteRepository
import com.example.notetakinapp.viewModel.NoteViewModel
import com.example.notetakinapp.viewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    public lateinit var binding: ActivityMainBinding
    public lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(
            NoteDatabase(this)
        )
        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)

        noteViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(NoteViewModel::class.java)
    }
}