package com.hola.appcountries.di

import android.content.Context
import androidx.room.Room
import com.hola.appcountries.data.database.MealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val MEAL_DATABASE_NAME = "meal_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MealDatabase::class.java, MEAL_DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideMealDataDao(db:MealDatabase) = db.getMealDataDao()


    @Singleton
    @Provides
    fun provideMealDetailDao(db:MealDatabase) = db.getMealDetailDao()

    @Singleton
    @Provides
    fun provideCategoryDao(db:MealDatabase) = db.getCategoryDao()
}