package ru.notes

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.notes.databinding.FragmentDetailedScreenBinding

class DetailedScreenFragment: Fragment(R.layout.fragment_detailed_screen) {
    private var binding: FragmentDetailedScreenBinding? = null
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteDao: NoteDao
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getInt("ARG_ID")
        noteDatabase = NoteDatabase.getDatabase(requireContext())
        noteDao = noteDatabase.noteDao()
    }

    @SuppressLint("ServiceCast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailedScreenBinding.bind(view)

        binding?.run {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                id?.let {
                    val note = noteDao.getNoteById(it)
                    withContext(Dispatchers.Main) {
                        etNote.setText(note.text)
                    }
                }
            }
            arrowBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnSave.setOnClickListener {
                val text = etNote.text.toString()
                id?.let {
                    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                        noteDao.update(
                            Note(
                                id = it,
                                text = text,
                                remindAt = 0,
                            )
                        )
                    }
                    (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                        .hideSoftInputFromWindow(requireView().windowToken, 0)

                    Snackbar.make(requireView(), "Заметка успешно сохранена!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun bundle(id: Int): Bundle = Bundle().apply {
            putInt("ARG_ID", id)
        }
    }
}