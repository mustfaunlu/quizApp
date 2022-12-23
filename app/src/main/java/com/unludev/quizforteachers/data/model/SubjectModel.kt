package com.unludev.quizforteachers.data.model

import com.unludev.quizforteachers.data.local.DatabaseSubjectModel

data class SubjectModel(
    val id: Int,
    val subjectName: String
)

fun List<SubjectModel>.asDatabaseModel(): List<DatabaseSubjectModel> {
    return this.map {
        DatabaseSubjectModel(
            id = null,
            subjectName = it.subjectName,
            )
    }
}