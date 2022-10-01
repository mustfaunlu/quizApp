package com.unludev.quizforteachers

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.unludev.quizforteachers.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var subjectList: ArrayList<String>
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //?? code smells
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id) {
                R.id.entryFragment -> {binding.recyclerView.visibility = View.GONE}
                R.id.subjectFragment -> {binding.recyclerView.visibility = View.VISIBLE}
            }
        }


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


        //TODO("RecyclerView mainactiviy uzerinde duruyor, konuyu ogrenince subjectfragmenta bagla")
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = SubjectAdapter(subjectList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
    }
}