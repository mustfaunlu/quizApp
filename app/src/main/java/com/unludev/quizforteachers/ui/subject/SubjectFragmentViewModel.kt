package com.unludev.quizforteachers.ui.subject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unludev.quizforteachers.data.model.SubjectModel
import com.unludev.quizforteachers.data.remote.QuestionsApiUtils
import com.unludev.quizforteachers.ui.expert.QuestionApiStatus
import kotlinx.coroutines.launch

class SubjectFragmentViewModel: ViewModel() {

    private val _status = MutableLiveData<QuestionApiStatus>()
    val status: LiveData<QuestionApiStatus> = _status

    private val _subjects = MutableLiveData<List<SubjectModel>>()
    val subjects: LiveData<List<SubjectModel>> = _subjects

    private val _subject = MutableLiveData<SubjectModel>()
    val subject: LiveData<SubjectModel> = _subject

    fun getQSubjects() {
        viewModelScope.launch{
            _status.value = QuestionApiStatus.LOADING
            try {
                _subjects.value = QuestionsApiUtils.questionApiservice.getSubjects()
                _status.value = QuestionApiStatus.DONE
            } catch (e: Exception) {
                _status.value = QuestionApiStatus.ERROR
                println(e.message)
                _subjects.value = listOf()
            }
        }
    }

    fun onSubjectClicked(subject: SubjectModel) {
        _subject.value = subject

    }
}