package com.example.shopper.di

import android.content.Context
import androidx.room.Room
import com.example.shopper.model.db.AppDatabase
import com.example.shopper.model.db.ShoppingItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton fun provideAppDatabase(
        @ApplicationContext appContext: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, AppDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingItemDao(
        appDatabase: AppDatabase
    ): ShoppingItemDao {
        return appDatabase.shoppingItemDao()
    }
}