package com.unludev.quizforteachers.ui.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.unludev.quizforteachers.databinding.FragmentSubjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubjectFragment : Fragment() {
    private lateinit var binding: FragmentSubjectBinding
    private val viewModel: SubjectFragmentViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = SubjectListAdapter(SubjectListener { subject ->
            viewModel.onSubjectClicked(subject)
            val action = SubjectFragmentDirections.actionSubjectFragmentToExpertQuestionFragment(subject.id!!)
            findNavController().navigate(action)
        })

        return binding.root
    }
}

