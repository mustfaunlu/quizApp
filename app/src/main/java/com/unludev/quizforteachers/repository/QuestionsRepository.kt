package com.unludev.quizforteachers.repository


import com.unludev.quizforteachers.data.local.QuestionDao
import com.unludev.quizforteachers.data.local.asDomainModel
import com.unludev.quizforteachers.data.model.NetworkQuestionModel
import com.unludev.quizforteachers.data.model.asDatabaseModel
import com.unludev.quizforteachers.data.remote.QuestionsApiService
import com.unludev.quizforteachers.domain.DomainQuestionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionsRepository @Inject constructor(
    private val service: QuestionsApiService,
    private val questionDao: QuestionDao
    ) {
    val questions: Flow<List<DomainQuestionModel>> = questionDao.getQuestionsFromDatabase().map { it.asDomainModel() }
     suspend fun refreshQuestions(topicID: Int) {
        withContext(Dispatchers.IO) {
            questionDao.deleteAll()
            val questionFromNetwork = fetchQuestionsByArguments(topicID)
            questionDao.insertAllQuestions(questionFromNetwork.asDatabaseModel())
        }
    }

     private suspend fun fetchQuestionsByArguments(topicID: Int): List<NetworkQuestionModel> {
        return when (topicID) {
            1 -> service.getSubj1Questions()
            2 -> service.getSubj2Questions()
            3 -> service.getSubj3Questions()
            4 -> service.getSubj4Questions()
            5 -> service.getSubj5Questions()
            6 -> service.getSubj6Questions()
            7 -> service.getSubj7Questions()
            8 -> service.getSubj8Questions()
            9 -> service.getSubj9Questions()
            10 ->service.getSubj10Questions()
            11 -> service.getSubj11Questions()
            12 -> service.getSubj12Questions()
            13 -> service.getSubj13Questions()
            14 -> service.getSubj14Questions()
            15 -> service.getSubj15Questions()
            else -> service.getSubj15Questions()
        }

    }
}



