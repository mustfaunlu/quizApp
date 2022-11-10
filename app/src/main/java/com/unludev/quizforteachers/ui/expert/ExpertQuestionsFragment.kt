package com.unludev.quizforteachers.ui.expert

import android.annotation.SuppressLint
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
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_expert_questions,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getExpertQuestions()
        viewModel.setColor.observe(viewLifecycleOwner) {

            when (it) {
                "A" -> {
                    binding.tvAnswerA.setBackgroundColor(resources.getColor(R.color.green))
                    binding.tvAnswerA.setTextColor(resources.getColor(R.color.white))
                    binding.executePendingBindings()
                }
                "1" -> {
                    binding.tvAnswerA.setBackgroundColor(resources.getColor(R.color.red))
                    binding.tvAnswerA.setTextColor(resources.getColor(R.color.white))
                    binding.executePendingBindings()
                }
                "B" -> {
                    binding.tvAnswerB.setBackgroundColor(resources.getColor(R.color.green))
                    binding.tvAnswerB.setTextColor(resources.getColor(R.color.white))
                    binding.executePendingBindings()
                }
                "2" -> {
                    binding.tvAnswerB.setBackgroundColor(resources.getColor(R.color.red))
                    binding.tvAnswerB.setTextColor(resources.getColor(R.color.white))
                    binding.executePendingBindings()
                }
                "C" -> {
                    binding.tvAnswerC.setBackgroundColor(resources.getColor(R.color.green))
                    binding.tvAnswerC.setTextColor(resources.getColor(R.color.white))
                    binding.executePendingBindings()
                }
                "3" -> {
                    binding.tvAnswerC.setBackgroundColor(resources.getColor(R.color.red))
                    binding.tvAnswerC.setTextColor(resources.getColor(R.color.white))
                    binding.executePendingBindings()
                }
                "D" -> {
                    binding.tvAnswerD.setBackgroundColor(resources.getColor(R.color.green))
                    binding.tvAnswerD.setTextColor(resources.getColor(R.color.white))
                    binding.executePendingBindings()
                }
                "4" -> {
                    binding.tvAnswerD.setBackgroundColor(resources.getColor(R.color.red))
                    binding.tvAnswerD.setTextColor(resources.getColor(R.color.white))
                    binding.executePendingBindings()
                }
                "E" -> {
                    binding.tvAnswerE.setBackgroundColor(resources.getColor(R.color.green))
                    binding.tvAnswerE.setTextColor(resources.getColor(R.color.white))
                    binding.executePendingBindings()

                }
                "5" -> {
                    binding.tvAnswerE.setBackgroundColor(resources.getColor(R.color.red))
                    binding.tvAnswerE.setTextColor(resources.getColor(R.color.white))
                    binding.executePendingBindings()
                }
                "0" -> {
                    binding.apply {
                        tvAnswerA.setBackgroundColor(resources.getColor(R.color.white))
                        tvAnswerA.setTextColor(resources.getColor(R.color.black))
                        tvAnswerB.setBackgroundColor(resources.getColor(R.color.white))
                        tvAnswerB.setTextColor(resources.getColor(R.color.black))
                        tvAnswerC.setBackgroundColor(resources.getColor(R.color.white))
                        tvAnswerC.setTextColor(resources.getColor(R.color.black))
                        tvAnswerD.setBackgroundColor(resources.getColor(R.color.white))
                        tvAnswerD.setTextColor(resources.getColor(R.color.black))
                        tvAnswerE.setBackgroundColor(resources.getColor(R.color.white))
                        tvAnswerE.setTextColor(resources.getColor(R.color.black))
                    }


                }
            }
        }
    }
}

