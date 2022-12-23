package com.unludev.quizforteachers

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unludev.quizforteachers.domain.DomainSubjectModel
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
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DomainSubjectModel>?) {
    val adapter = recyclerView.adapter as SubjectListAdapter
    Log.d("data", data.toString())
    adapter.submitList(data)
}

//bunu yazinca hepsi ayni anda degisiyor drawable icinde selector olustulcak
//@BindingAdapter("customColorSet")
//fun TextView.setBackgroundOptions(setColor: String?) {
//
//
//    val buton = this.findViewById<TextView>(this.id)
//
//    buton.setOnClickListener {
//        when(setColor) {
//        "true" -> {
//            buton.setBackgroundColor(buton.resources.getColor(R.color.green))
//            buton.setTextColor(buton.resources.getColor(R.color.white))
//        }
//        "false" -> {
//            buton.setBackgroundColor(buton.resources.getColor(R.color.red))
//            buton.setTextColor(buton.resources.getColor(R.color.white))
//        }
//        "resetOptionsColors" -> {
//            buton.setBackgroundColor(buton.resources.getColor(R.color.white))
//            buton.setTextColor(buton.resources.getColor(R.color.black))
//        }
//    } }








