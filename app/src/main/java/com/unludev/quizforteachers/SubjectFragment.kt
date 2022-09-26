package com.unludev.quizforteachers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.unludev.quizforteachers.databinding.FragmentSubjectBinding

class SubjectFragment : Fragment() {
 lateinit var binding: FragmentSubjectBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSubjectBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSubject1.setOnClickListener {
            val action = SubjectFragmentDirections.actionSubjectFragmentToExpertQuestionFragment("s1")
            view.findNavController().navigate(action)

        }
        binding.tvSubject2.setOnClickListener {
            val action = SubjectFragmentDirections.actionSubjectFragmentToExpertQuestionFragment("s2")
            view.findNavController().navigate(action)

        }
    }

}