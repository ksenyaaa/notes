package ru.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.notes.databinding.FragmentMainScreenBinding

class MainScreenFragment: Fragment(R.layout.fragment_main_screen) {
    private var binding: FragmentMainScreenBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainScreenBinding.bind(view)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}