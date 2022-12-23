package com.unludev.quizforteachers.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllQuestions(questions: List<DatabaseQuestionModel>)

    @Query("SELECT * FROM questions ")
    fun getQuestionsFromDatabase(): Flow<List<DatabaseQuestionModel>>

    @Query("DELETE FROM questions")
    fun deleteAll()
}



@Database(entities = arrayOf(DatabaseQuestionModel::class), version = 1, exportSchema = false)
abstract class QuestionsDatabase : RoomDatabase() {
    abstract val questionDao: QuestionDao
}
