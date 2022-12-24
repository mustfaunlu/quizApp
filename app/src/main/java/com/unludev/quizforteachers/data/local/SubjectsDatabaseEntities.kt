package com.unludev.quizforteachers.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.unludev.quizforteachers.domain.DomainSubjectModel

@Entity(tableName = "subjects")
data class DatabaseSubjectModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,

    @ColumnInfo(name = "subjectName")
    val subjectName: String?
)

fun List<DatabaseSubjectModel>.asDomainModel(): List<DomainSubjectModel> {
    return map {
        DomainSubjectModel(
            id = it.id,
            subjectName = it.subjectName
        )

    }
}