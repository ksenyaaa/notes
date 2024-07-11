package ru.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.notes.databinding.ItemHabitBinding

class HabitAdapter(
    private val habits: List<Habit>,
    private val onItemClick: (Habit) -> Unit,
    private val onLongClick: (Habit) -> Unit,
) : RecyclerView.Adapter<HabitHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitHolder {
        return HabitHolder(
            ItemHabitBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ), onItemClick, onLongClick
        )
    }

    override fun onBindViewHolder(holder: HabitHolder, position: Int) {
        holder.bind(habits[position])
    }

    override fun getItemCount(): Int = habits.size
}
