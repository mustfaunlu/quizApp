package com.unludev.quizforteachers.ui.expert

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ExpertQuestionViewModelFactory(private val topic: Int, private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpertQuestionsViewModel::class.java)) {
            return ExpertQuestionsViewModel(topic, application) as T
        } else {
            throw IllegalStateException("Can not create instance of this ViewModel")
        }
    }
}
