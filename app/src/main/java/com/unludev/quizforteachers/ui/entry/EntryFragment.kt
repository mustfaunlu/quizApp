package com.unludev.quizforteachers.ui.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.unludev.quizforteachers.databinding.FragmentEntryBinding


class EntryFragment : Fragment() {
lateinit var binding: FragmentEntryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntryBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnExpert.setOnClickListener{
            val action =
                EntryFragmentDirections
                    .actionEntryFragmentToSubjectFragment()
            view.findNavController().navigate(action)
        }



//        binding.btnHeadmaster.setOnClickListener{
//            val action =
//                EntryFragmentDirections
//                    .actionEntryFragmentToHeadMasterFragment()
//            view.findNavController().navigate(action)
//        }


    }

}