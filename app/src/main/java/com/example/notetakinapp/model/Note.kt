package com.example.notetakinapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note (
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val noteTitle: String,
    @ColumnInfo(name = "body")
    val noteBody: String
): Parcelable