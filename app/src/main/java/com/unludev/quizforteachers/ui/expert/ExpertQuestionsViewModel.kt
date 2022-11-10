package com.unludev.quizforteachers.ui.expert

import android.os.CountDownTimer
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

    private val _setColor = MutableLiveData<String>("0")
    val setColor: LiveData<String> = _setColor

    var currentQuestion = 0

    init {
        getExpertQuestions()
    }
    fun getExpertQuestions() {
        viewModelScope.launch {
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


    fun clickA() {
        if ( _questions.value?.get(currentQuestion)?.answerA == _questions.value?.get(currentQuestion)?.correctAnswer) {
            _setColor.value = "A"
        } else {
            _setColor.value = "1"
        }
        setOptions()
    }
    fun clickB() {
        if ( _questions.value?.get(currentQuestion)?.answerB == _questions.value?.get(currentQuestion)?.correctAnswer) {
            _setColor.value = "B"
        } else {
            _setColor.value = "2"
        }
        setOptions()
    }
    fun clickC() {
        if ( _questions.value?.get(currentQuestion)?.answerC == _questions.value?.get(currentQuestion)?.correctAnswer) {
            _setColor.value = "C"
        } else {
            _setColor.value = "3"
        }
        setOptions()
    }
    fun clickD() {
        if ( _questions.value?.get(currentQuestion)?.answerD == _questions.value?.get(currentQuestion)?.correctAnswer) {
            _setColor.value = "D"
        } else {
            _setColor.value = "4"
        }

        setOptions()
    }
    fun clickE() {
        if ( _questions.value?.get(currentQuestion)?.answerE == _questions.value?.get(currentQuestion)?.correctAnswer) {
            _setColor.value = "E"
        } else {
            _setColor.value = "5"
        }
        setOptions()
    }

    private fun setOptions() {
        if (currentQuestion < _questions.value?.size!! - 1) {
            object : CountDownTimer(5000,1000) {
                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {
                    _setColor.value = "0"
                    currentQuestion += 1
                    getExpertQuestions()
                }

            }.start()

        } else {

        }

    }
}