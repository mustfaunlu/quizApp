package com.unludev.quizforteachers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unludev.quizforteachers.databinding.FragmentSubjectBinding


class SubjectFragment : Fragment() {
 private lateinit var binding: FragmentSubjectBinding
    private lateinit var subjectList: ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSubjectBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subjectList = ArrayList()
        subjectList.add("Öğrenme ve Öğretme Süreçleri")
        subjectList.add("Ölçme ve Değerlendirme")
        subjectList.add("Özel Eğitim ve Rehberlik")
        subjectList.add("Eğitim Araştırmaları ve Ar-Ge Çalışmaları")
        subjectList.add("Eğitimde Kapsayıcılık")
        subjectList.add("Çevre Eğitimi ve İklim Değişikliği")
        subjectList.add("Sosyal Etkileşim ve İletişim")
        subjectList.add("Dijital Yetkinlik")
        subjectList.add("Güvenli Okul ve Okul Güvenliği")
        subjectList.add("Okul Geliştirme ve Liderlik")
        subjectList.add("Sosyal Duygusal Öğrenme Becerilerin Geliştirilmesi")
        subjectList.add("Bilişsel Düşünme Becerileri")



        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RcyclerAdapter(subjectList)
        binding.recyclerView.adapter = adapter

    }

}

