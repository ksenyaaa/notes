package ru.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.notes.databinding.ItemNoteBinding

class NoteAdapter(
    private val list: List<Note>,
    private val onItemClick: (Int) -> Unit,
): RecyclerView.Adapter<NoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick,
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.onBind(list[position])
    }
}