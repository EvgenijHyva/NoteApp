package com.example.notetakinapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notetakinapp.model.Note

@Database(entities = [Note::class], version = 1) // all entities
abstract class NoteDatabase: RoomDatabase() { // extend
    abstract fun getNoteDao(): NoteDAO

    companion object { // design singleton pattern (run time 1 instance)
        @Volatile // mark JVM, for making this field visible for any threads
        private var instance: NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
            synchronized(LOCK) { // multithreading
                instance ?:
                createDatabase(context).also {
                    instance = it
                }
            }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "note_db"
        ).build()
    }
}