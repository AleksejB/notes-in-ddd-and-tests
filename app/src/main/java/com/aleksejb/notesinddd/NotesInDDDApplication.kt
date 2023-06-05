package com.aleksejb.notesinddd

import android.app.Application
import com.aleksejb.dependancy.injection.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotesInDDDApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NotesInDDDApplication)
            modules(appModule)
        }
    }
}