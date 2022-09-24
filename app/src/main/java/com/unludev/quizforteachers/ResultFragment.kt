package com.unludev.quizforteachers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.unludev.quizforteachers.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val correct = ResultFragmentArgs.fromBundle(it).correct
            binding.tvResultCorrect.text = correct
            val wrong = ResultFragmentArgs.fromBundle(it).wrong
            binding.tvResultWrong.text = wrong
        }

        binding.tvReturnHomepage.setOnClickListener {
            val action =
                ResultFragmentDirections
                    .actionResultFragmentToEntryFragment()
            view.findNavController().navigate(action)
        }


    }

}