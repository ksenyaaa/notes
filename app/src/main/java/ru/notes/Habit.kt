package ru.notes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val isDone: Boolean,
    val remindAt: Long,
)
