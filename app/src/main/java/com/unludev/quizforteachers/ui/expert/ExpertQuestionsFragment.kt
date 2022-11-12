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


class ExpertQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentExpertQuestionsBinding
    private val args: ExpertQuestionsFragmentArgs by navArgs()
    private val viewModel: ExpertQuestionsViewModel by viewModels {
        ExpertQuestionViewModelFactory(args.que)

    }

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

        getQuestions()

        viewModel.setColor.observe(viewLifecycleOwner) {
            setBackgroundOptions(it)
        }
        viewModel.isThereQuestion.observe(viewLifecycleOwner) {
            if (it == false) {
                onResult()
            }
        }
    }

    private fun getQuestions() {
        when (args.que) {
            "Eğitimde Kapsayıcılık - 1" -> viewModel.getExpertQuestions()
            else -> viewModel.getExpertQuestions1()
        }


    }

    private fun setBackgroundOptions(it: String?) {
        when (it) {
            "trueOptionA" -> {
                binding.tvAnswerA.setBackgroundColor(resources.getColor(R.color.green))
                binding.tvAnswerA.setTextColor(resources.getColor(R.color.white))
                binding.executePendingBindings()
            }
            "falseOptionA" -> {
                binding.tvAnswerA.setBackgroundColor(resources.getColor(R.color.red))
                binding.tvAnswerA.setTextColor(resources.getColor(R.color.white))
                binding.executePendingBindings()
            }
            "trueOptionB" -> {
                binding.tvAnswerB.setBackgroundColor(resources.getColor(R.color.green))
                binding.tvAnswerB.setTextColor(resources.getColor(R.color.white))
                binding.executePendingBindings()
            }
            "falseOptionB" -> {
                binding.tvAnswerB.setBackgroundColor(resources.getColor(R.color.red))
                binding.tvAnswerB.setTextColor(resources.getColor(R.color.white))
                binding.executePendingBindings()
            }
            "trueOptionC" -> {
                binding.tvAnswerC.setBackgroundColor(resources.getColor(R.color.green))
                binding.tvAnswerC.setTextColor(resources.getColor(R.color.white))
                binding.executePendingBindings()
            }
            "falseOptionC" -> {
                binding.tvAnswerC.setBackgroundColor(resources.getColor(R.color.red))
                binding.tvAnswerC.setTextColor(resources.getColor(R.color.white))
                binding.executePendingBindings()
            }
            "trueOptionD" -> {
                binding.tvAnswerD.setBackgroundColor(resources.getColor(R.color.green))
                binding.tvAnswerD.setTextColor(resources.getColor(R.color.white))
                binding.executePendingBindings()
            }
            "falseOptionD" -> {
                binding.tvAnswerD.setBackgroundColor(resources.getColor(R.color.red))
                binding.tvAnswerD.setTextColor(resources.getColor(R.color.white))
                binding.executePendingBindings()
            }
            "trueOptionE" -> {
                binding.tvAnswerE.setBackgroundColor(resources.getColor(R.color.green))
                binding.tvAnswerE.setTextColor(resources.getColor(R.color.white))
                binding.executePendingBindings()

            }
            "falseOptionE" -> {
                binding.tvAnswerE.setBackgroundColor(resources.getColor(R.color.red))
                binding.tvAnswerE.setTextColor(resources.getColor(R.color.white))
                binding.executePendingBindings()
            }
            "resetOptionsColors" -> {
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
            }
        }
    }

    private fun onResult() {
        val action =
            ExpertQuestionsFragmentDirections.actionExpertQuestionFragmentToResultFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setDefaultCurrentQuestionIndex()
    }

}

