package com.unludev.quizforteachers

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unludev.quizforteachers.data.model.SubjectModel
import com.unludev.quizforteachers.ui.expert.QuestionApiStatus

@BindingAdapter("questionApiStatus")
fun bindStatus(statusImgView: ImageView, status: QuestionApiStatus?) {
    when (status) {
        QuestionApiStatus.DONE -> {
            statusImgView.visibility = View.GONE
        }
        QuestionApiStatus.LOADING -> {
            statusImgView.visibility = View.VISIBLE
            statusImgView.setImageResource(R.drawable.loading_animation)
        }
        QuestionApiStatus.ERROR -> {
            statusImgView.visibility = View.VISIBLE
            statusImgView.setImageResource(R.drawable.ic_connection_error)
        }
        else -> {}
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SubjectModel>?) {
    val adapter = recyclerView.adapter as SubjectListAdapter
    adapter.submitList(data)
}


