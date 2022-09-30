package com.unludev.quizforteachers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.unludev.quizforteachers.databinding.FragmentSubjectBinding


class SubjectFragment : Fragment() {
 private lateinit var binding: FragmentSubjectBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

}

