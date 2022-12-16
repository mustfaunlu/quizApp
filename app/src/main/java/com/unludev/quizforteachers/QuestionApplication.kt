package com.unludev.quizforteachers

import android.app.Application
import timber.log.Timber

class QuestionApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}