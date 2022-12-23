package com.unludev.quizforteachers.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.unludev.quizforteachers.domain.DomainQuestionModel

@Entity(tableName = "questions")
data class DatabaseQuestionModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "question")
    val question: String?,

    @ColumnInfo(name = "questionHead")
    val questionHead: String?,

    @ColumnInfo(name = "answerA")
    val answerA: String?,

    @ColumnInfo(name = "answerB")
    val answerB: String?,

    @ColumnInfo(name = "answerC")
    val answerC: String?,

    @ColumnInfo(name = "answerD")
    val answerD: String?,

    @ColumnInfo(name = "answerE")
    val answerE: String?,

    @ColumnInfo(name = "correctAnswer")
    val correctAnswer: String?
)

/**
 * Map DatabaseVideos to domain entities
 */
fun List<DatabaseQuestionModel>.asDomainModel(): List<DomainQuestionModel> {
    return map {
        DomainQuestionModel(
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