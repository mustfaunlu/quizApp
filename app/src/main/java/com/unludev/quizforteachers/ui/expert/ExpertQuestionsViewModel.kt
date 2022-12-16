package com.unludev.quizforteachers.ui.expert

import android.app.Application
import android.os.CountDownTimer
import android.os.SystemClock
import androidx.lifecycle.*
import com.unludev.quizforteachers.data.local.getDatabase
import com.unludev.quizforteachers.data.model.NetworkQuestionModel
import com.unludev.quizforteachers.domain.DomainQuestionModel
import com.unludev.quizforteachers.repository.QuestionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class QuestionApiStatus { LOADING, ERROR, DONE }

class ExpertQuestionsViewModel(topic: Int, application: Application) :
    AndroidViewModel(application) {

    private val _status = MutableLiveData<QuestionApiStatus>()
    val status: LiveData<QuestionApiStatus> = _status

    private val _question = MutableLiveData<NetworkQuestionModel>()
    val question: LiveData<NetworkQuestionModel> = _question

    private val _setColor = MutableLiveData("resetOptionsColors")
    val setColor: LiveData<String> = _setColor

    private val _currentQuestion = MutableLiveData(0)
    val currentQuestion: LiveData<Int> get() = _currentQuestion

    private val _correct = MutableLiveData(0)
    val correct: LiveData<Int> get() = _correct

    private val _wrong = MutableLiveData(0)
    val wrong: LiveData<Int> get() = _wrong

    private val _isThereQuestion = MutableLiveData(true)
    val isThereQuestion: LiveData<Boolean> get() = _isThereQuestion

    private var doubleClickLastTime = 0L

    private val questionsRepository: QuestionsRepository

    val questionsData: LiveData<List<DomainQuestionModel>>

    init {
        questionsRepository = QuestionsRepository(getDatabase(application))
        questionsData = questionsRepository.questions.asLiveData()
        loadQuestions(topic)
    }


    private fun loadQuestions(topic: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) { _status.value = QuestionApiStatus.LOADING }
            try {
                questionsRepository.refreshQuestions(topic)
                withContext(Dispatchers.Main) { _status.value = QuestionApiStatus.DONE }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { _status.value = QuestionApiStatus.ERROR }
            }
        }
    }

    fun setOptionsColor(
        option: String?,
        correctAnswer: String?,
        trueClickValue: String,
        falseClickValue: String
    ) {
        if (SystemClock.elapsedRealtime() - doubleClickLastTime < 3000) {
            return
        }
        doubleClickLastTime = SystemClock.elapsedRealtime()

        if (option == correctAnswer) {
            _setColor.value = trueClickValue
            _correct.value = _correct.value?.plus(1)
        } else {
            _setColor.value = falseClickValue
            _wrong.value = _wrong.value?.plus(1)
        }
        setOptions()
    }

    private fun setOptions() {
        if (_currentQuestion.value!! < questionsData.value?.size!! - 1) {
            object : CountDownTimer(3000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    _setColor.value = "resetOptionsColors"
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