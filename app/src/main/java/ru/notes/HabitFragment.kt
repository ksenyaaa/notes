package ru.notes
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HabitFragment : Fragment(R.layout.fragment_habit) {

    private lateinit var habitAdapter: HabitAdapter
    private lateinit var habitRecyclerView: RecyclerView
    private lateinit var habitDatabase: HabitDatabase
    private lateinit var habitDao: HabitDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        habitRecyclerView = view.findViewById(R.id.habit_recycler_view)
        habitRecyclerView.layoutManager = LinearLayoutManager(context)
        habitDatabase = HabitDatabase.getDatabase(requireContext())
        habitDao = habitDatabase.habitDao()

        habitDao.getAllHabits().observe(viewLifecycleOwner) { habits ->
            habitAdapter = HabitAdapter(habits)
            habitRecyclerView.adapter = habitAdapter
        }
    }
}