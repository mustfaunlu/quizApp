package com.unludev.quizforteachers
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.unludev.quizforteachers.databinding.FragmentSubjectBinding
import com.unludev.quizforteachers.databinding.RvRowBinding

class RcyclerAdapter(private val subjectList: ArrayList<String>): RecyclerView.Adapter<RcyclerAdapter.SubjectVH>() {

    class SubjectVH(itemView: View) : ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectVH {

     val itemview = LayoutInflater.from(parent.context).inflate(R.layout.rv_row,parent,false)
        return SubjectVH(itemview)
    }

    override fun onBindViewHolder(holder: SubjectVH, position: Int) {
       val textview = holder.itemView.findViewById<TextView>(R.id.rv_tv)
        textview.text = subjectList[position]
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }
}