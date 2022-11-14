package com.unludev.quizforteachers.ui.expert

import android.app.Application
import android.os.CountDownTimer
import android.os.SystemClock
import androidx.lifecycle.*
import com.unludev.quizforteachers.data.local.getDatabase
import com.unludev.quizforteachers.data.model.NetworkQuestionModel
import com.unludev.quizforteachers.data.remote.QuestionsApiUtils
import com.unludev.quizforteachers.domain.DomainQuestionModel
import com.unludev.quizforteachers.repository.QuestionsRepository
import kotlinx.coroutines.launch

enum class QuestionApiStatus { LOADING, ERROR, DONE }

class ExpertQuestionsViewModel(private val que: String, application: Application) : AndroidViewModel(application) {

    private val _status = MutableLiveData<QuestionApiStatus>()
    val status: LiveData<QuestionApiStatus> = _status

    private val _questions = MutableLiveData<List<NetworkQuestionModel>>()
    val questions: LiveData<List<NetworkQuestionModel>> = _questions

    private val _question = MutableLiveData<NetworkQuestionModel>()
    val question: LiveData<NetworkQuestionModel> = _question

    private val _setColor = MutableLiveData<String>("resetOptionsColors")
    val setColor: LiveData<String> = _setColor

    private val _currentQuestion = MutableLiveData<Int>(0)
    val currentQuestion: LiveData<Int> get() = _currentQuestion

    private val _correct = MutableLiveData<Int>(0)
    val correct: LiveData<Int> get() = _correct

    private val _wrong = MutableLiveData<Int>(0)
    val wrong: LiveData<Int> get() = _wrong

    private val _isThereQuestion = MutableLiveData<Boolean>(true)
    val isThereQuestion: LiveData<Boolean> get() = _isThereQuestion

    var doubleClickLastTime = 0L

    private val questionsRepository: QuestionsRepository = QuestionsRepository(getDatabase(application))

    val q: LiveData<List<DomainQuestionModel>> get() = questionsRepository.questions.asLiveData()

    init {
        //buraya fetchQuestionbyargumentti yaz fragmenttakini iptal et dene olacak mi
    }

     fun fetchQuestionsByArguments() { // bu logic repos tasinacak
        when (que) {
            "Eğitimde Kapsayıcılık - 1" -> getExpertQuestions()
            else -> getExpertQuestions1()
        }

    }

    private fun getExpertQuestions() {
        viewModelScope.launch {
            _status.value = QuestionApiStatus.LOADING
            try {
                 questionsRepository.refreshQuestions()
                _status.value = QuestionApiStatus.DONE
            } catch (e: Exception) {
                _status.value = QuestionApiStatus.ERROR
            }
        }
    }

    private fun getExpertQuestions1() {
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

    fun setOptionsColor(option: String?, correctAnswer: String?, trueClickValue: String, falseClickValue: String) {
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
        if (_currentQuestion.value!! < _questions.value?.size!! - 1) {
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