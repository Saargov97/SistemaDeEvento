package com.example.easycheckin.hilt.database

import android.content.Context
import androidx.room.Room
import com.example.routemap.database.AppDatabase
import com.example.routemap.database.DAO.EventoDAO
import com.example.routemap.database.DAO.InscricaoDAO
import com.example.routemap.database.DAO.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.MainScope
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object EasycheckinRoomDatabaseModule {

    @Provides
    fun provideAppRoomDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getDatabase(context, MainScope())

    @Provides
    fun provideUserDao(appRoomDatabase: AppDatabase): UserDAO =
        appRoomDatabase.userDAO()

    @Provides
    fun provideEventoDao(appRoomDatabase: AppDatabase): EventoDAO =
        appRoomDatabase.eventoDAO()

    @Provides
    fun provideInscricaoDao(appRoomDatabase: AppDatabase): InscricaoDAO =
        appRoomDatabase.inscricaoDAO()
}