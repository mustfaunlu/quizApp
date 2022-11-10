package com.unludev.quizforteachers.ui.expert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unludev.quizforteachers.data.model.QuestionModel
import com.unludev.quizforteachers.data.remote.QuestionsApiUtils
import kotlinx.coroutines.launch

enum class QuestionApiStatus { LOADING, ERROR, DONE }

class ExpertQuestionsViewModel : ViewModel() {
    private val _status = MutableLiveData<QuestionApiStatus>()
    val status: LiveData<QuestionApiStatus> = _status

    private val _questions = MutableLiveData<List<QuestionModel>>()
    val questions: LiveData<List<QuestionModel>> = _questions

    private val _question = MutableLiveData<QuestionModel>()
    val question: LiveData<QuestionModel> = _question





    fun getExpertQuestions() {
        viewModelScope.launch{
            _status.value = QuestionApiStatus.LOADING
            try {
                _questions.value = QuestionsApiUtils.questionApiservice.getQuestions()
                _status.value = QuestionApiStatus.DONE
            } catch (e: Exception) {
                _status.value = QuestionApiStatus.ERROR
                _questions.value = listOf()
            }
        }
    }




    fun setOptions(current: Int) {
        println(current)
        if (current < _questions.value?.size!! - 1) {
            println(_questions.value?.size!! - 1)
            current + 1
            getExpertQuestions()
            println(current)
    }


   }

}