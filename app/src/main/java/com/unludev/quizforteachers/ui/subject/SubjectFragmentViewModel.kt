package com.unludev.quizforteachers.ui.subject

import android.util.Log
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
class SubjectFragmentViewModel @Inject constructor(private val subjectRepository: SubjectRepository): ViewModel() {
    private val _status = MutableLiveData<QuestionApiStatus>()
    val status: LiveData<QuestionApiStatus> = _status

    private val _subject = MutableLiveData<DomainSubjectModel>()
    val subject: LiveData<DomainSubjectModel> = _subject

    val subjectData: LiveData<List<DomainSubjectModel>> = subjectRepository.subjects.asLiveData()

     init {
         loadSubjects()
         Log.d("subjects", subject.toString())
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