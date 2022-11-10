package com.unludev.quizforteachers.ui.expert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.unludev.quizforteachers.R
import com.unludev.quizforteachers.databinding.FragmentExpertQuestionsBinding


class ExpertQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentExpertQuestionsBinding
    private val viewModel: ExpertQuestionsViewModel by activityViewModels()
    val args: ExpertQuestionsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_expert_questions, container, false)
        viewModel.getExpertQuestions()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

}