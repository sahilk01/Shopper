package com.example.shopper.di

import com.example.shopper.model.datasource.ShoppingDbDataSource
import com.example.shopper.model.datasource.ShoppingItemDataSource
import com.example.shopper.model.db.ShoppingItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ShoppingDataSourceModule {
    @Provides
    fun provideShoppingDataSources(
        shoppingDbDataSource: ShoppingDbDataSource
    ): Array<ShoppingItemDataSource> {
        return arrayOf(
           shoppingDbDataSource
        )
    }
}