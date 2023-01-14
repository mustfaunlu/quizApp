package com.unludev.quizforteachers.ui.subject

import androidx.lifecycle.*
import com.unludev.quizforteachers.domain.DomainSubjectModel
import com.unludev.quizforteachers.repository.SubjectRepository
import com.unludev.quizforteachers.ui.expert.QuestionApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SubjectFragmentViewModel @Inject constructor(private val subjectRepository: SubjectRepository) :
    ViewModel() {
    private val _status = MutableLiveData<QuestionApiStatus>()
    val status: LiveData<QuestionApiStatus> get() = _status

    private val _subject = MutableLiveData<DomainSubjectModel>()
    val subject: LiveData<DomainSubjectModel> get() = _subject

    val subjectData: LiveData<List<DomainSubjectModel>> get() = subjectRepository.subjects.asLiveData()

    init {
        loadSubjects()
    }

    private fun loadSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) { _status.value = QuestionApiStatus.LOADING }
            try {
                subjectRepository.refreshSubjects()
                withContext(Dispatchers.Main) { _status.value = QuestionApiStatus.DONE }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { _status.value = QuestionApiStatus.ERROR }
            }
        }
    }

    fun onSubjectClicked(subject: DomainSubjectModel) {
        _subject.value = subject
    }
}