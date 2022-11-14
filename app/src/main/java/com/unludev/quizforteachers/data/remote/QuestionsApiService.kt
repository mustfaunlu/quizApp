package com.unludev.quizforteachers.data.remote

import com.unludev.quizforteachers.data.model.NetworkQuestionModel
import com.unludev.quizforteachers.data.model.SubjectModel
import retrofit2.http.GET

interface QuestionsApiService {
        @GET("db2.json")
        suspend fun getQuestions(): List<NetworkQuestionModel>

        @GET("db3.json")
        suspend fun getQuestions1(): List<NetworkQuestionModel>

        @GET("db.json")
        suspend fun getSubjects(): List<SubjectModel>

}