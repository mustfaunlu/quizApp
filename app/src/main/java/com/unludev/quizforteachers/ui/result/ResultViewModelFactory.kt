package com.unludev.quizforteachers.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactory(private val wrong: Int, private val correct: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultFragmentViewModel::class.java)) {
            return ResultFragmentViewModel(wrong, correct) as T
        } else {
            throw IllegalStateException("Can not create instance of this ViewModel")
        }
    }
}