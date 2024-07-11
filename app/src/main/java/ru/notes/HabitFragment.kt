package ru.notes

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.notes.databinding.FragmentHabitBinding
import ru.notes.databinding.FragmentMainScreenBinding
import java.util.Calendar

class HabitFragment : Fragment(R.layout.fragment_habit) {

    private var binding: FragmentHabitBinding? = null
    private lateinit var habitAdapter: HabitAdapter
    private lateinit var habitDatabase: HabitDatabase
    private lateinit var habitDao: HabitDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        habitDatabase = HabitDatabase.getDatabase(requireContext())
        habitDao = habitDatabase.habitDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHabitBinding.bind(view)
        habitDao.getAllHabits().observe(viewLifecycleOwner) { habits ->
            println(habits)
            if (habits.isEmpty()) {
                habitAdapter = HabitAdapter(emptyHabits, ::onItemClick, ::onLongClick)
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    emptyHabits.forEach { habitDao.insert(it) }
                }
            } else {
                habitAdapter = HabitAdapter(habits, ::onItemClick, ::onLongClick)
            }
            binding?.run { rvHabit.adapter = habitAdapter }
        }
    }

    private fun onItemClick(habit: Habit) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val updHabit = habit.copy(isDone = habit.isDone)
            habitDao.insert(updHabit)
        }
    }
    private fun onLongClick(habit: Habit) {
        showDatePicker("Не забудь выполнить", habit.name, 0)
    }

    fun showDatePicker(title: String, text: String, id: Int) {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        DatePickerDialog(requireContext(), { _, year, month, day ->
            TimePickerDialog(requireContext(), { _, hour, minute ->
                val pickedDateTime = Calendar.getInstance()
                pickedDateTime.set(year, month, day, hour, minute)
                setReminder(pickedDateTime.timeInMillis, title, text, id)
            }, startHour, startMinute, true).show()
        }, startYear, startMonth, startDay).show()
    }

    fun setReminder(remindAt: Long?, title: String, text: String, id: Int) {
        val intent = Intent(requireContext(), ReminderBroadcast::class.java).apply {
            putExtra("ARG1", title)
            putExtra("ARG2", text)
            putExtra("ARG3", id)
        }
        val pendingIntent =
            PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        println(remindAt)


        if (remindAt == null) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                60000,
                360000,
                pendingIntent,
            )
        } else {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                remindAt,
                pendingIntent,
            )
        }
    }


    private companion object {
        val emptyHabits = listOf(
            Habit(
                id = 1,
                name = "Drink water",
                isDone = false,
                remindAt = 0,
            ),
            Habit(
                id = 2,
                name = "Do exercises",
                isDone = false,
                remindAt = 0,
            ),
            Habit(
                id = 3,
                name = "Read a book",
                isDone = false,
                remindAt = 0,
            ),
            Habit(
                id = 4,
                name = "Улыбайся чаще",
                isDone = false,
                remindAt = 0,
            ),
        )
    }
}

