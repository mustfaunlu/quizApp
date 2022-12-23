package com.unludev.quizforteachers.ui.expert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.unludev.quizforteachers.R
import com.unludev.quizforteachers.databinding.FragmentExpertQuestionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpertQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentExpertQuestionsBinding
    private val args: ExpertQuestionsFragmentArgs by navArgs()

    private val viewModel: ExpertQuestionsViewModel by viewModels()



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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setColor.observe(viewLifecycleOwner) {
            setBackgroundOptions(it)
        }
        viewModel.isThereQuestion.observe(viewLifecycleOwner) {
            if (it == false) {
                onResult()
            }
        }
    }

    private fun setBackgroundOptions(color: String?) {
        val textView = when (color) {
            "trueOptionA", "falseOptionA" -> binding.tvAnswerA
            "trueOptionB", "falseOptionB" -> binding.tvAnswerB
            "trueOptionC", "falseOptionC" -> binding.tvAnswerC
            "trueOptionD", "falseOptionD" -> binding.tvAnswerD
            "trueOptionE", "falseOptionE" -> binding.tvAnswerE
            else -> {
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
                    binding.executePendingBindings()
                }
                return
            }
        }
        if (color.startsWith("true")) {
            textView.setBackgroundColor(resources.getColor(R.color.green))
        } else {
            textView.setBackgroundColor(resources.getColor(R.color.red))
        }
        textView.setTextColor(resources.getColor(R.color.white))
        binding.executePendingBindings()

    }

    private fun onResult() {
        val action =
            ExpertQuestionsFragmentDirections.actionExpertQuestionFragmentToResultFragment(
                viewModel.correct.value!!,
                viewModel.wrong.value!!
            )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setDefaultCurrentQuestionIndex()
    }

}

