package com.unludev.quizforteachers.repository


import com.unludev.quizforteachers.data.local.QuestionsDatabase
import com.unludev.quizforteachers.data.local.asDomainModel
import com.unludev.quizforteachers.data.model.asDatabaseModel
import com.unludev.quizforteachers.data.remote.QuestionsApiUtils
import com.unludev.quizforteachers.domain.DomainQuestionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class QuestionsRepository(private val database: QuestionsDatabase) {
    val questions: Flow<List<DomainQuestionModel>> = database.questionDao.getQuestionsFromDatabase().map { it.asDomainModel() }
    suspend fun refreshQuestions() {
        withContext(Dispatchers.IO) {
            val questionFromNetwork = QuestionsApiUtils.questionApiservice.getQuestions() // her ekranda bu istek sorulari doner burayi hallet
            database.questionDao.deleteAll()
            database.questionDao.insertAllQuestions(questionFromNetwork.asDatabaseModel())
        }
    }
}



