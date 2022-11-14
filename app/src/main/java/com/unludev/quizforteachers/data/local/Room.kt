package com.unludev.quizforteachers.data.local

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllQuestions(questions: List<DatabaseQuestionModel>)

    @Query("SELECT * FROM questions ")
    fun getQuestionsFromDatabase(): Flow<List<DatabaseQuestionModel>>
}



@Database(entities = arrayOf(DatabaseQuestionModel::class), version = 1)
abstract class QuestionsDatabase : RoomDatabase() {
    abstract val questionDao: QuestionDao
}

private lateinit var INSTANCE: QuestionsDatabase

fun getDatabase(context: Context): QuestionsDatabase {
    synchronized(QuestionsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                QuestionsDatabase::class.java,
                "questions"
            ).build()
        }
    }
    return INSTANCE
}