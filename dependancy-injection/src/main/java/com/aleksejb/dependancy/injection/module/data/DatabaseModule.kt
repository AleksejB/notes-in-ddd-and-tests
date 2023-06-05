package com.aleksejb.dependancy.injection.module.data

import android.content.Context
import androidx.room.Room
import com.aleksejb.core.data.database.NotesInDDDDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { provideNotesInDDDDatabase(androidContext()) }

    factory { (get(clazz = NotesInDDDDatabase::class) as NotesInDDDDatabase).getCheckboxNoteDao() }
//    factory { (get(clazz = NotesInDDDDatabase::class) as NotesInDDDDatabase).getImageNoteDao() }
    factory { (get(clazz = NotesInDDDDatabase::class) as NotesInDDDDatabase).getTextNoteDao() }
}

private fun provideNotesInDDDDatabase(context: Context): NotesInDDDDatabase {
    return Room.databaseBuilder(
        context,
        NotesInDDDDatabase::class.java,
        "${context.packageName}.db"
    )
        .fallbackToDestructiveMigration()
        .build()
}