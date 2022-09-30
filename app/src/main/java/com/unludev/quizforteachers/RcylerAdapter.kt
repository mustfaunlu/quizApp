package com.unludev.quizforteachers


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unludev.quizforteachers.databinding.RvRowBinding


class SubjectAdapter(private val subjectList: ArrayList<String>): RecyclerView.Adapter<SubjectAdapter.SubjectVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectVH {
     val binding = RvRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return SubjectVH(binding)
    }

    override fun onBindViewHolder(holder: SubjectVH, position: Int) = holder.bind(subjectList[position])

    override fun getItemCount(): Int {
        return subjectList.size
    }

    class SubjectVH(private val binding: RvRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.rvTv.text = item
        }
    }
}