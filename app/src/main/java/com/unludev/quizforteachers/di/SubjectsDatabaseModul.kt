package com.unludev.quizforteachers.di

import android.content.Context
import androidx.room.Room
import com.unludev.quizforteachers.data.local.SubjectDatabase
import com.unludev.quizforteachers.data.local.SubjectsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SubjectsDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : SubjectDatabase {
        return Room.databaseBuilder(
            appContext,
            SubjectDatabase::class.java,
            "subjects"
        ).build()
    }

    @Provides
    fun provideDao(db: SubjectDatabase): SubjectsDao {
        return db.subjectsDao
    }
}