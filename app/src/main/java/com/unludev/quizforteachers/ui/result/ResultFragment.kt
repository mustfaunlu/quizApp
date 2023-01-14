package com.unludev.quizforteachers.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.unludev.quizforteachers.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val args: ResultFragmentArgs by navArgs()
    private val viewModel: ResultFragmentViewModel by viewModels {
        ResultViewModelFactory(args.wrong, args.correct)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
        binding.lifecycleOwner = this
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            with(binding){
                tvResultCorrect.text = args.correct.toString()
                tvResultWrong.text = args.wrong.toString()
            }

        binding.tvReturnHomepage.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToEntryFragment()
            findNavController().navigate(action)
        }
    }


}