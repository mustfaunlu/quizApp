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

    val questions: Flow<List<DomainQuestionModel>> =
        questionDao.getQuestionsFromDatabase().map { it.asDomainModel() }

    suspend fun fetchAndStoreQuestionsForTopic(topicID: Int) {
        withContext(Dispatchers.IO) {
            questionDao.deleteAll()
            val questionFromNetwork = fetchQuestionsByTopic(topicID)
            questionDao.insertAllQuestions(questionFromNetwork.asDatabaseModel())
        }
    }

    private val topicApiCallMap = mapOf(
        1 to service::getSubj1Questions,
        2 to service::getSubj2Questions,
        3 to service::getSubj3Questions,
        4 to service::getSubj4Questions,
        5 to service::getSubj5Questions,
        6 to service::getSubj6Questions,
        7 to service::getSubj7Questions,
        8 to service::getSubj8Questions,
        9 to service::getSubj9Questions,
        10 to service::getSubj10Questions,
        11 to service::getSubj11Questions,
        12 to service::getSubj12Questions,
        13 to service::getSubj13Questions,
        14 to service::getSubj14Questions,
        15 to service::getSubj15Questions,
    )

    private suspend fun fetchQuestionsByTopic(topicID: Int): List<NetworkQuestionModel> {
        val apiCall = topicApiCallMap[topicID] ?: return emptyList()
        return apiCall()
    }
}

