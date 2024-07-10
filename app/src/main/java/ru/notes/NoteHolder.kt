package ru.notes

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.notes.databinding.ItemNoteBinding

class NoteHolder(
    private val binding: ItemNoteBinding
): ViewHolder(binding.root) {

    fun onBind(note: ru.notes.Note) {
        binding.run {

        }
    }
}