package ru.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HabitAdapter(private val habits: List<Habit>) : RecyclerView.Adapter<HabitHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HabitHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: HabitHolder, position: Int) {
        val habit: Habit = habits[position]
        holder.bind(habit)
    }

    override fun getItemCount(): Int = habits.size
}