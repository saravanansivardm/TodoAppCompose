package com.example.todoappstevdza.di

import android.content.Context
import androidx.room.Room
import com.example.todoappstevdza.data.dao.TodoDAO
import com.example.todoappstevdza.data.db.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideDAO(todoDatabase: TodoDatabase): TodoDAO {
        return todoDatabase.todoDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase =
        Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todos_tbl"
        ).fallbackToDestructiveMigration().build()

}
