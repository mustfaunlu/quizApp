package com.unludev.quizforteachers


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.unludev.quizforteachers.databinding.RvRowBinding


class SubjectAdapter(private val subjectList: ArrayList<String>): RecyclerView.Adapter<SubjectAdapter.SubjectVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectVH {
     val binding = RvRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return SubjectVH(binding)
    }

    override fun onBindViewHolder(holder: SubjectVH, position: Int) {
        holder.bind(subjectList[position])
//TODO(" when icindekileri bir enum yaz hem sayilara hem que lere")
        holder.binding.root.setOnClickListener{
            when(position) {
                0  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Eğitimde Kapsayıcılık - 1")
                    holder.binding.rvTv.findNavController().navigate(action)}
                1  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Eğitimde Kapsayıcılık - 2")
                    holder.binding.rvTv.findNavController().navigate(action)}
                2  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Eğitim Araştırmaları ve Ar-Ge Çalışmaları - 1")
                    holder.binding.rvTv.findNavController().navigate(action)}
                3  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Eğitim Araştırmaları ve Ar-Ge Çalışmaları - 2")
                    holder.binding.rvTv.findNavController().navigate(action)}
                4  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Güvenli Okul ve Okul Güvenliği - 1")
                    holder.binding.rvTv.findNavController().navigate(action)}
                5  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Güvenli Okul ve Okul Güvenliği - 2")
                    holder.binding.rvTv.findNavController().navigate(action)}
                6  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Sosyal Etkileşim ve İletişim - 1")
                    holder.binding.rvTv.findNavController().navigate(action)}
                7  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Sosyal Etkileşim ve İletişim - 2")
                    holder.binding.rvTv.findNavController().navigate(action)}
                8  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Sosyal Etkileşim ve İletişim - 3")
                    holder.binding.rvTv.findNavController().navigate(action)}
                9  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Dijital Yetkinlik")
                    holder.binding.rvTv.findNavController().navigate(action)}
                10  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Çevre Eğitimi ve İklim Değişikliği")
                    holder.binding.rvTv.findNavController().navigate(action)}
                11  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Özel Eğitim ve Rehberlik - 1")
                    holder.binding.rvTv.findNavController().navigate(action)}
                12  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Özel Eğitim ve Rehberlik - 2")
                    holder.binding.rvTv.findNavController().navigate(action)}
                13  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Öğrenme ve Öğretme Süreçleri - 1")
                    holder.binding.rvTv.findNavController().navigate(action)}
                14  -> {val action =
                    SubjectFragmentDirections
                        .actionSubjectFragmentToExpertQuestionFragment("Öğrenme ve Öğretme Süreçleri - 2")
                    holder.binding.rvTv.findNavController().navigate(action)}
            }


        }
    }


    override fun getItemCount(): Int {
        return subjectList.size
    }

    inner class SubjectVH( val binding: RvRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.rvTv.text = item
        }
    }
}