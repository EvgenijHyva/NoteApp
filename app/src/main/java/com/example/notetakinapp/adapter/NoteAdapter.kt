package com.example.notetakinapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakinapp.databinding.NoteItemBinding
import com.example.notetakinapp.fragments.HomeDirections
import com.example.notetakinapp.model.Note
import java.util.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(val itemBinding: NoteItemBinding):
        RecyclerView.ViewHolder(itemBinding.root) // layout: note_item.xml

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        // calculate updates for recycler view adapter
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteBody == newItem.noteBody &&
                    oldItem.noteTitle == newItem.noteTitle
        }
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback) // data synchronization

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.tvNoteTitle.text = currentNote.noteTitle
        holder.itemBinding.tvNoteBody.text = currentNote.noteBody

        val random = Random()
        val color = Color.argb(255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256))

        holder.itemBinding.ibColor.setBackgroundColor(color)
        holder.itemView.setOnClickListener {
            val direction = HomeDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)

            it.findNavController().navigate(direction) // view in this case
        }
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }
}