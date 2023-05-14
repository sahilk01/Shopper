@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.shopper.veiwModel

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.shopper.model.datasource.ShoppingDbDataSource
import com.example.shopper.model.datasource.ShoppingItemDataSource
import com.example.shopper.model.db.AppDatabase
import com.example.shopper.model.db.ShoppingItemDao
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.repository.shoppingDetail.ShoppingDetailRepositoryImpl
import com.example.shopper.model.repository.shoppingDetail.ShoppingItemDetailRepository
import com.example.shopper.model.repository.shoppingList.ShoppingListRepository
import com.example.shopper.model.repository.shoppingList.ShoppingListRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class ShoppingListViewModelTest {

    private lateinit var appDatabase: AppDatabase
    lateinit var shoppingItemDao: ShoppingItemDao
    private lateinit var shoppingItemDataSource: ShoppingItemDataSource
    lateinit var shoppingListRepository: ShoppingListRepository
    lateinit var shoppingListViewModel: ShoppingListViewModel

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        shoppingItemDao = appDatabase.shoppingItemDao()
        shoppingItemDataSource = ShoppingDbDataSource(shoppingItemDao)
        val dataSources = arrayOf(shoppingItemDataSource)
        shoppingListRepository = ShoppingListRepositoryImpl(dataSources)
        val shoppingDetailRepository = ShoppingDetailRepositoryImpl(dataSources)
        shoppingListViewModel = ShoppingListViewModel(shoppingListRepository, shoppingDetailRepository)
    }

    @Test
    fun insertShoppingItem_expectedSingleItem() = runTest {
        val item  = ShoppingItem.dummyItem
        shoppingItemDataSource.addShoppingItem(item)
        shoppingListViewModel.search("")
        assertEquals(1, shoppingListViewModel.fullShoppingList.value.size)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }
}