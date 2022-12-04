package com.unludev.quizforteachers.data.remote

import com.unludev.quizforteachers.data.model.NetworkQuestionModel
import com.unludev.quizforteachers.data.model.SubjectModel
import retrofit2.http.GET

interface QuestionsApiService {

        @GET("db.json")
        suspend fun getSubjects(): List<SubjectModel>

        @GET("egtkapsam.json")
        suspend fun getSubj1Questions(): List<NetworkQuestionModel>

        @GET("egtmarge.json")
        suspend fun getSubj2Questions(): List<NetworkQuestionModel>

        @GET("egtmarge2.json")
        suspend fun getSubj3Questions(): List<NetworkQuestionModel>

        @GET("guvokul.json")
        suspend fun getSubj4Questions(): List<NetworkQuestionModel>

        @GET("guvokul2.json")
        suspend fun getSubj5Questions(): List<NetworkQuestionModel>

        @GET("sosyaletki.json")
        suspend fun getSubj6Questions(): List<NetworkQuestionModel>

        @GET("sosyaletki2.json")
        suspend fun getSubj7Questions(): List<NetworkQuestionModel>

        @GET("dijyet.json")
        suspend fun getSubj8Questions(): List<NetworkQuestionModel>

        @GET("ceviklim.json")
        suspend fun getSubj9Questions(): List<NetworkQuestionModel>

        @GET("ozelegtm.json")
        suspend fun getSubj10Questions(): List<NetworkQuestionModel>

        @GET("ozelegtm2.json")
        suspend fun getSubj11Questions(): List<NetworkQuestionModel>

        @GET("ogrsur.json")
        suspend fun getSubj12Questions(): List<NetworkQuestionModel>

        @GET("ogrsur2.json")
        suspend fun getSubj13Questions(): List<NetworkQuestionModel>

        @GET("olcmevedeg.json")
        suspend fun getSubj14Questions(): List<NetworkQuestionModel>

        @GET("olcmevedeg2.json")
        suspend fun getSubj15Questions(): List<NetworkQuestionModel>


}