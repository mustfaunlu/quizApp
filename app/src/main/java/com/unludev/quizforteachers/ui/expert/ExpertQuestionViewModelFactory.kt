package com.unludev.quizforteachers.ui.expert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ExpertQuestionViewModelFactory(private val que: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpertQuestionsViewModel::class.java)) {
            return ExpertQuestionsViewModel(que) as T
        } else {
            throw IllegalStateException("Can not create instance of this ViewModel")
        }
    }
}
