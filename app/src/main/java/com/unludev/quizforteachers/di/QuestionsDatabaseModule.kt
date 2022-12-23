package com.unludev.quizforteachers.di

import android.content.Context
import androidx.room.Room
import com.unludev.quizforteachers.data.local.QuestionDao
import com.unludev.quizforteachers.data.local.QuestionsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuestionsDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : QuestionsDatabase {
        return Room.databaseBuilder(
            appContext,
            QuestionsDatabase::class.java,
            "questions"
        ).build()
    }

    @Provides
    fun provideDao(db: QuestionsDatabase): QuestionDao {
        return db.questionDao
    }
}