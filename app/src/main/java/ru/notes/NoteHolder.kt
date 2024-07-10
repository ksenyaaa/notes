package ru.notes

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.notes.databinding.ItemNoteBinding

class NoteHolder(
    private val binding: ItemNoteBinding
): ViewHolder(binding.root) {

    fun onBind(note: Note) {
        binding.run {
            tvText.text = note.text
            if (note.id == 7) tvTitle.isVisible = true
        }
    }
}