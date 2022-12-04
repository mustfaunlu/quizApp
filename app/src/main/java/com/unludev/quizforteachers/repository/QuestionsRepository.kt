package com.unludev.quizforteachers.repository


import com.unludev.quizforteachers.data.local.QuestionsDatabase
import com.unludev.quizforteachers.data.local.asDomainModel
import com.unludev.quizforteachers.data.model.NetworkQuestionModel
import com.unludev.quizforteachers.data.model.asDatabaseModel
import com.unludev.quizforteachers.data.remote.QuestionsApiUtils
import com.unludev.quizforteachers.domain.DomainQuestionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class QuestionsRepository(private val database: QuestionsDatabase) {
    val questions: Flow<List<DomainQuestionModel>> = database.questionDao.getQuestionsFromDatabase().map { it.asDomainModel() }
     suspend fun refreshQuestions(topic: Int) {
        withContext(Dispatchers.IO) {
            database.questionDao.deleteAll()
            val questionFromNetwork = fetchQuestionsByArguments(topic)
            database.questionDao.insertAllQuestions(questionFromNetwork.asDatabaseModel())
        }
    }

     private suspend fun fetchQuestionsByArguments(topic: Int): List<NetworkQuestionModel> {
        return when (topic) {
            1 -> QuestionsApiUtils.questionApiservice.getSubj1Questions()
            2 -> QuestionsApiUtils.questionApiservice.getSubj2Questions()
            3 -> QuestionsApiUtils.questionApiservice.getSubj3Questions()
            4 -> QuestionsApiUtils.questionApiservice.getSubj4Questions()
            5 -> QuestionsApiUtils.questionApiservice.getSubj5Questions()
            6 -> QuestionsApiUtils.questionApiservice.getSubj6Questions()
            7 -> QuestionsApiUtils.questionApiservice.getSubj7Questions()
            8 -> QuestionsApiUtils.questionApiservice.getSubj8Questions()
            9 -> QuestionsApiUtils.questionApiservice.getSubj9Questions()
            10 -> QuestionsApiUtils.questionApiservice.getSubj10Questions()
            11 -> QuestionsApiUtils.questionApiservice.getSubj11Questions()
            12 -> QuestionsApiUtils.questionApiservice.getSubj12Questions()
            13 -> QuestionsApiUtils.questionApiservice.getSubj13Questions()
            14 -> QuestionsApiUtils.questionApiservice.getSubj14Questions()
            15 -> QuestionsApiUtils.questionApiservice.getSubj15Questions()
            else -> QuestionsApiUtils.questionApiservice.getSubj15Questions()
        }

    }
}



