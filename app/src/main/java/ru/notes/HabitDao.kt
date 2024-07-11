package ru.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habit: Habit)

    @Update
    suspend fun update(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Query("SELECT * FROM habits ORDER BY id DESC")
    fun getAllHabits(): LiveData<List<Habit>>
}
