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

class ExpertQuestionsViewModel(private val quee: String) : ViewModel() {

    private val _status = MutableLiveData<QuestionApiStatus>()
    val status: LiveData<QuestionApiStatus> = _status

    private val _questions = MutableLiveData<List<QuestionModel>>()
    val questions: LiveData<List<QuestionModel>> = _questions

    private val _question = MutableLiveData<QuestionModel>()
    val question: LiveData<QuestionModel> = _question

    private val _setColor = MutableLiveData<String>("0")
    val setColor: LiveData<String> = _setColor

    private val _currentQuestion = MutableLiveData<Int>(0)
    val currentQuestion: LiveData<Int> get() = _currentQuestion

    private val _correct = MutableLiveData<Int>()
    val correct: LiveData<Int> get() = _correct

    private val _wrong = MutableLiveData<Int>()
    val wrong: LiveData<Int> get() = _wrong

    private val _isThereQuestion = MutableLiveData<Boolean>(true)
    val isThereQuestion: LiveData<Boolean> get() = _isThereQuestion


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

    fun getExpertQuestions1() {
        viewModelScope.launch {
            _status.value = QuestionApiStatus.LOADING
            try {
                _questions.value = QuestionsApiUtils.questionApiservice.getQuestions1()
                _status.value = QuestionApiStatus.DONE
            } catch (e: Exception) {
                _status.value = QuestionApiStatus.ERROR
                _questions.value = listOf()
            }
        }
    }


    fun clickA() {
        if (_questions.value?.get(_currentQuestion.value!!)?.answerA == _questions.value?.get(
                _currentQuestion.value!!
            )?.correctAnswer
        ) {
            _setColor.value = "A"
            _correct.value = _correct.value?.plus(1)
        } else {
            _setColor.value = "1"
            _wrong.value = _wrong.value?.plus(1)
        }
        setOptions()
    }

    fun clickB() {
        if (_questions.value?.get(_currentQuestion.value!!)?.answerB == _questions.value?.get(
                _currentQuestion.value!!
            )?.correctAnswer
        ) {
            _setColor.value = "B"
            _correct.value = _correct.value?.plus(1)
        } else {
            _setColor.value = "2"
            _wrong.value = _wrong.value?.plus(1)
        }
        setOptions()
    }

    fun clickC() {
        if (_questions.value?.get(_currentQuestion.value!!)?.answerC == _questions.value?.get(
                _currentQuestion.value!!
            )?.correctAnswer
        ) {
            _setColor.value = "C"
            _correct.value = _correct.value?.plus(1)
        } else {
            _setColor.value = "3"
            _wrong.value = _wrong.value?.plus(1)
        }
        setOptions()
    }

    fun clickD() {
        if (_questions.value?.get(_currentQuestion.value!!)?.answerD == _questions.value?.get(
                _currentQuestion.value!!
            )?.correctAnswer
        ) {
            _setColor.value = "D"
            _correct.value = _correct.value?.plus(1)
        } else {
            _setColor.value = "4"
            _wrong.value = _wrong.value?.plus(1)
        }
        setOptions()
    }

    fun clickE() {
        if (_questions.value?.get(_currentQuestion.value!!)?.answerE == _questions.value?.get(
                _currentQuestion.value!!
            )?.correctAnswer
        ) {
            _setColor.value = "E"
            _correct.value = _correct.value?.plus(1)
        } else {
            _setColor.value = "5"
            _wrong.value = _wrong.value?.plus(1)
        }
        setOptions()
    }

    private fun setOptions() {
        if (_currentQuestion.value!! < _questions.value?.size!! - 1) {
            object : CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    _setColor.value = "0"
                    _currentQuestion.value = _currentQuestion.value!!.plus(1)
                }
            }.start()
        } else {
            _isThereQuestion.value = false
        }
    }

    fun setDefaultCurrentQuestionIndex() {
        _currentQuestion.value = 0
    }
}