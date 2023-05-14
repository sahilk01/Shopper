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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class ShoppingItemDetailViewModelTest {

    private lateinit var appDatabase: AppDatabase
    lateinit var shoppingItemDao: ShoppingItemDao
    private lateinit var shoppingItemDataSource: ShoppingItemDataSource
    lateinit var shoppingDetailRepository: ShoppingItemDetailRepository
    lateinit var shoppingItemDetailViewModel: ShoppingItemDetailViewModel

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        shoppingItemDao = appDatabase.shoppingItemDao()
        shoppingItemDataSource = ShoppingDbDataSource(shoppingItemDao)
        shoppingDetailRepository = ShoppingDetailRepositoryImpl(arrayOf(shoppingItemDataSource))
        shoppingItemDetailViewModel = ShoppingItemDetailViewModel(shoppingDetailRepository)
    }

    @Test
    fun saveShoppingItem() = runBlocking {
        val item = ShoppingItem.dummyItem
        shoppingItemDetailViewModel.setShoppingItem(item)
        shoppingItemDetailViewModel.saveShoppingItem()
        val result = shoppingItemDataSource.getAllShoppingItems().first()
        assertEquals(1, result.size)
        assertEquals(item.copy(1), result[0])
    }


    @Test
    fun updateShoppingItem() = runBlocking {
        val item = ShoppingItem.dummyItem
        shoppingItemDataSource.addShoppingItem(item)
        val savedItem = shoppingItemDataSource.getAllShoppingItems().first()[0]
        assertEquals(item.copy(1), savedItem)
        shoppingItemDetailViewModel.setShoppingItem(savedItem.copy(quantity = 20))
        shoppingItemDetailViewModel.saveShoppingItem()
        val updatedItem = shoppingItemDataSource.getShoppingItem(savedItem.id ?: 0).first()
        assertEquals(20, updatedItem.quantity)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }
}