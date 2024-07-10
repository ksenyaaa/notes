package ru.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_note, parent, false)) {
    private var nameView: TextView? = null
    private var descriptionView: TextView? = null

    init {
        nameView = itemView.findViewById(R.id.item_note)
        descriptionView = itemView.findViewById(R.id.detailedScreenFragment)
    }

    fun bind(habit: Habit) {
        nameView?.text = habit.name
        descriptionView?.text = habit.description
    }
}