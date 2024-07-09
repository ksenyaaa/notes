package ru.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.notes.databinding.FragmentMainScreenBinding
import ru.notes.databinding.FragmentTrackerBinding

class TrackerFragment: Fragment(R.layout.fragment_tracker) {
    private var binding: FragmentTrackerBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTrackerBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}


