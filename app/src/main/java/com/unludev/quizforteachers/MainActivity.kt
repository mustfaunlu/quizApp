package com.unludev.quizforteachers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.unludev.quizforteachers.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var subjectList: ArrayList<String>
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



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
            "Bilişsel Düşünme Becerileri")


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = SubjectAdapter(subjectList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
    }


}