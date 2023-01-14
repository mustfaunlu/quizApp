package com.unludev.quizforteachers.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSubjects(subjects: List<DatabaseSubjectModel>)

    @Query("SELECT * FROM subjects")
    fun getSubjectsFromDatabase(): Flow<List<DatabaseSubjectModel>>

    @Query("DELETE FROM subjects")
    fun deleteAllSubjects()
}

@Database(entities = [DatabaseSubjectModel::class], version = 1, exportSchema = false)
abstract class SubjectDatabase : RoomDatabase() {
    abstract val subjectsDao: SubjectsDao
}
