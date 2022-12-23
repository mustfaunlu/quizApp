package com.unludev.quizforteachers.domain


data class DomainQuestionModel(
    val question: String?,
    val questionHead: String?,
    val answerA: String?,
    val answerB: String?,
    val answerC: String?,
    val answerD: String?,
    val answerE: String?,
    val correctAnswer: String?
)

data class DomainSubjectModel(
    val id: Int?,
    val subjectName: String?,
)