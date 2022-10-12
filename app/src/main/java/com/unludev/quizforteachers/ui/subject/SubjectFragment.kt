package com.unludev.quizforteachers.ui.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.unludev.quizforteachers.SubjectAdapter
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

//TODO("hardcoded stringleri xml e bagla enum yaz")
        subjectList = arrayListOf("Eğitimde Kapsayıcılık - 1",
            "Eğitimde Kapsayıcılık - 2",
            "Eğitim Araştırmaları ve Ar-Ge Çalışmaları - 1",
            "Eğitim Araştırmaları ve Ar-Ge Çalışmaları - 2",
            "Güvenli Okul ve Okul Güvenliği - 1",
            "Güvenli Okul ve Okul Güvenliği - 2",
            "Sosyal Etkileşim ve İletişim - 1",
            "Sosyal Etkileşim ve İletişim - 2",
            "Sosyal Etkileşim ve İletişim - 3",
            "Dijital Yetkinlik",
            "Çevre Eğitimi ve İklim Değişikliği",
            "Özel Eğitim ve Rehberlik - 1",
            "Özel Eğitim ve Rehberlik - 2",
            "Öğrenme ve Öğretme Süreçleri - 1",
            "Öğrenme ve Öğretme Süreçleri - 2",
            "Ölçme ve Değerlendirme - 1",
            "Ölçme ve Değerlendirme - 2",
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = SubjectAdapter(subjectList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
    }

}

