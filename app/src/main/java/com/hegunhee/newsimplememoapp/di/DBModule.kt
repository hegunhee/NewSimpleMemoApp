package com.hegunhee.newsimplememoapp.di

import android.content.Context
import androidx.room.Room
import com.hegunhee.newsimplememoapp.data.DB.MemoDatabase
import com.hegunhee.newsimplememoapp.data.Dao.MemoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Singleton
    @Provides
    fun provideMemoDatabase(
        @ApplicationContext context: Context
    ): MemoDatabase = Room
        .databaseBuilder(context, MemoDatabase::class.java, MemoDatabase.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun provideMemoDao(memoDatabase: MemoDatabase) : MemoDao = memoDatabase.dao()
}