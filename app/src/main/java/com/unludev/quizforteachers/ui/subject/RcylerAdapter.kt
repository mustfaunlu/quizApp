package com.unludev.quizforteachers


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unludev.quizforteachers.data.model.SubjectModel
import com.unludev.quizforteachers.databinding.RvRowBinding


class SubjectListAdapter(val clickListener: SubjectListener) :
    ListAdapter<SubjectModel, SubjectListAdapter.SubjectViewHolder>(DiffCallback){

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
     val binding = RvRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = getItem(position)
        holder.bind(clickListener,subject)

    }
    companion object DiffCallback : DiffUtil.ItemCallback<SubjectModel>() {

        override fun areItemsTheSame(oldItem: SubjectModel, newItem: SubjectModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SubjectModel, newItem: SubjectModel): Boolean {
            return oldItem == newItem
        }

    }


    inner class SubjectViewHolder( val binding: RvRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: SubjectListener, subject: SubjectModel) {
            binding.subject= subject
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }
    }
}

class SubjectListener(val clickListener: (subject: SubjectModel) -> Unit) {
    fun onClick(subject: SubjectModel) = clickListener(subject)
}