package com.example.shopper.di

import com.example.shopper.model.datasource.ShoppingDbDataSource
import com.example.shopper.model.datasource.ShoppingItemDataSource
import com.example.shopper.model.db.ShoppingItemDao
import com.example.shopper.model.repository.shoppingDetail.ShoppingDetailRepositoryImpl
import com.example.shopper.model.repository.shoppingDetail.ShoppingItemDetailRepository
import com.example.shopper.model.repository.shoppingList.ShoppingListRepository
import com.example.shopper.model.repository.shoppingList.ShoppingListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ShoppingItemsModule {

    @Binds
    abstract fun provideShoppingShowcaseRepository(
        shoppingShowcaseRepository: ShoppingListRepositoryImpl
    ): ShoppingListRepository

    @Binds
    abstract fun provideItemDetailRepository(
        shoppingDetailRepository: ShoppingDetailRepositoryImpl
    ): ShoppingItemDetailRepository

//    @Binds
//    abstract fun provideShoppingDbDataSource(
//        shoppingDbDataSource: ShoppingDbDataSource
//    ): ShoppingItemDataSource
}