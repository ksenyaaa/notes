package ru.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.notes.databinding.FragmentDetailedScreenBinding
import ru.notes.databinding.FragmentMainScreenBinding

class DetailedScreenFragment: Fragment(R.layout.fragment_detailed_screen) {
    private var binding: FragmentDetailedScreenBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailedScreenBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}