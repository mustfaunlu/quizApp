package com.unludev.quizforteachers.repository

import com.unludev.quizforteachers.data.local.SubjectsDao
import com.unludev.quizforteachers.data.local.asDomainModel
import com.unludev.quizforteachers.data.model.asDatabaseModel
import com.unludev.quizforteachers.data.remote.QuestionsApiService
import com.unludev.quizforteachers.domain.DomainSubjectModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubjectRepository @Inject constructor(
    private val service: QuestionsApiService,
    private val subjectsDao: SubjectsDao
){
    val subjects: Flow<List<DomainSubjectModel>> = subjectsDao.getSubjectsFromDatabase().map { it.asDomainModel() }
    suspend fun refreshSubjects() {
        withContext(Dispatchers.IO) {
            val subjectFromNetwork = service.getSubjects()
            subjectsDao.insertAllSubjects(subjectFromNetwork.asDatabaseModel())
        }
    }

}