package com.unludev.quizforteachers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.unludev.quizforteachers.databinding.FragmentSubjectBinding


class SubjectFragment : Fragment() {
    private lateinit var subjectList: ArrayList<String>
 private lateinit var binding: FragmentSubjectBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//TODO("hardcoded stringleri xml e bagla")
        subjectList = arrayListOf("Öğrenme ve Öğretme Süreçleri",
            "Ölçme ve Değerlendirme",
            "Özel Eğitim ve Rehberlik",
            "Eğitim Araştırmaları ve Ar-Ge Çalışmaları",
            "Eğitimde Kapsayıcılık",
            "Çevre Eğitimi ve İklim Değişikliği",
            "Sosyal Etkileşim ve İletişim",
            "Dijital Yetkinlik",
            "Güvenli Okul ve Okul Güvenliği",
            "Okul Geliştirme ve Liderlik",
            "Sosyal Duygusal Öğrenme Becerilerin Geliştirilmesi",
            "Bilişsel Düşünme Becerileri"
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = SubjectAdapter(subjectList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
    }

}

