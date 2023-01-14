package com.unludev.quizforteachers.data.model


import com.unludev.quizforteachers.data.local.DatabaseQuestionModel



data class NetworkQuestionModel(
    val question: String?,
    val questionHead: String?,
    val answerA: String?,
    val answerB: String?,
    val answerC: String?,
    val answerD: String?,
    val answerE: String?,
    val correctAnswer: String?
)

/**
 * Convert Network results to database objects
 */
fun List<NetworkQuestionModel>.asDatabaseModel(): List<DatabaseQuestionModel> {
    return this.map {
        DatabaseQuestionModel(
            id = null,
            question = it.question,
            questionHead = it.questionHead,
            answerA = it.answerA,
            answerB = it.answerB,
            answerC = it.answerC,
            answerD = it.answerD,
            answerE = it.answerE,
            correctAnswer = it.correctAnswer

        )
    }
}





