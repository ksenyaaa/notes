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
import kotlinx.coroutines.runBlocking
import ru.notes.databinding.FragmentMainScreenBinding
import java.util.Calendar

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private var binding: FragmentMainScreenBinding? = null
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteDatabase = NoteDatabase.getDatabase(requireContext())
        noteDao = noteDatabase.noteDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainScreenBinding.bind(view)
        noteDao.getAllNotes().observe(viewLifecycleOwner) { notes ->
            if (notes.isEmpty()) {
                noteAdapter = NoteAdapter(emptyNotes)
                runBlocking { emptyNotes.forEach { noteDao.insert(it) } }
            } else {
                noteAdapter = NoteAdapter(notes)
            }
            binding?.run { rvlist.adapter = noteAdapter }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun showDatePicker(title: String, text: String, id: Int) {
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

    private fun setReminder(remindAt: Long, title: String, text: String, id: Int) {
        val intent = Intent(requireContext(), ReminderBroadcast::class.java).apply {
            putExtra("ARG1", title)
            putExtra("ARG2", text)
            putExtra("ARG3", id)
        }
        val pendingIntent =
            PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            remindAt,
            pendingIntent,
        )
    }

    private companion object {
        val emptyNotes = listOf(
            Note(
                id = 0,
                text = "",
                remindAt = 0,
            ),
            Note(
                id = 1,
                text = "",
                remindAt = 0,
            ),
            Note(
                id = 2,
                text = "",
                remindAt = 0,
            ),
            Note(
                id = 3,
                text = "",
                remindAt = 0,
            ),
            Note(
                id = 4,
                text = "",
                remindAt = 0,
            ),
            Note(
                id = 5,
                text = "",
                remindAt = 0,
            ),
            Note(
                id = 6,
                text = "",
                remindAt = 0,
            ),
            Note(
                id = 7,
                text = "",
                remindAt = 0,
            ),
        )
    }
}
