package com.unludev.quizforteachers.ui.expert

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ExpertQuestionViewModelFactory(private val que: String, private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpertQuestionsViewModel::class.java)) {
            return ExpertQuestionsViewModel(que, application) as T
        } else {
            throw IllegalStateException("Can not create instance of this ViewModel")
        }
    }
}
