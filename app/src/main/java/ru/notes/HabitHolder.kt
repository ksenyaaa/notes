package ru.notes

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.notes.databinding.ItemHabitBinding

class HabitHolder(
    private val binding: ItemHabitBinding,
    private val onItemClick: (Habit) -> Unit,
    private val onLongClick: (Habit) -> Unit,
) : ViewHolder(binding.root) {

    fun bind(habit: Habit) {
        binding.run {
            item.setOnClickListener {
                onItemClick(habit.copy(isDone = isChecked.isChecked))
                println(isChecked.isChecked)
            }
            isChecked.setOnClickListener {
                onItemClick(habit.copy(isDone = isChecked.isChecked))
                println(isChecked.isChecked)
            }
            tvName.setOnClickListener {
                onItemClick(habit.copy(isDone = isChecked.isChecked))
                println(isChecked.isChecked)
            }
            isChecked.isChecked = habit.isDone
            tvName.text = habit.name
            tvName.setOnLongClickListener {
                onLongClick(habit)
                true
            }
        }
    }
}
