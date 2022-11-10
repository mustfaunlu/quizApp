package com.unludev.quizforteachers.data.remote

import com.unludev.quizforteachers.data.model.QuestionModel
import com.unludev.quizforteachers.data.model.SubjectModel
import retrofit2.http.GET

interface QuestionsApiService {
        @GET("db2.json")
        suspend fun getQuestions(): List<QuestionModel>

        @GET("db.json")
        suspend fun getSubjects(): List<SubjectModel>

}