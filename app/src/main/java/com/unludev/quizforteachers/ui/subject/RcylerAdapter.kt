package com.unludev.quizforteachers.ui.subject


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.unludev.quizforteachers.databinding.RvRowBinding
import com.unludev.quizforteachers.domain.DomainSubjectModel


class SubjectListAdapter(private val clickListener: SubjectListener) :
    androidx.recyclerview.widget.ListAdapter<DomainSubjectModel, SubjectListAdapter.SubjectViewHolder>(
        DiffCallback
    ) {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val binding = RvRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = getItem(position)
        holder.bind(clickListener, subject)

    }

    companion object DiffCallback : DiffUtil.ItemCallback<DomainSubjectModel>() {

        override fun areItemsTheSame(
            oldItem: DomainSubjectModel,
            newItem: DomainSubjectModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DomainSubjectModel,
            newItem: DomainSubjectModel
        ): Boolean {
            return oldItem == newItem
        }

    }


    inner class SubjectViewHolder(val binding: RvRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: SubjectListener, subject: DomainSubjectModel) {
            binding.subject = subject
            binding.rvTv.text = subject.subjectName
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }
    }
}

class SubjectListener(val clickListener: (subject: DomainSubjectModel) -> Unit) {
    fun onClick(subject: DomainSubjectModel) = clickListener(subject)
}