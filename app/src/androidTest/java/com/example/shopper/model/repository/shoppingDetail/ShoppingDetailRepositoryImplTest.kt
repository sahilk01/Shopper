package com.example.shopper.model.repository.shoppingDetail

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.shopper.model.datasource.ShoppingDbDataSource
import com.example.shopper.model.datasource.ShoppingItemDataSource
import com.example.shopper.model.db.AppDatabase
import com.example.shopper.model.db.ShoppingItemDao
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.repository.shoppingList.ShoppingListRepository
import com.example.shopper.model.repository.shoppingList.ShoppingListRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ShoppingDetailRepositoryImplTest {

    private lateinit var appDatabase: AppDatabase
    lateinit var shoppingItemDao: ShoppingItemDao
    private lateinit var shoppingItemDataSource: ShoppingItemDataSource
    lateinit var shoppingDetailRepository: ShoppingItemDetailRepository

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        shoppingItemDao = appDatabase.shoppingItemDao()
        shoppingItemDataSource = ShoppingDbDataSource(shoppingItemDao)
        shoppingDetailRepository = ShoppingDetailRepositoryImpl(arrayOf(shoppingItemDataSource))
    }


    @Test
    fun insertShoppingItem_expectedSingleItem() = runBlocking {
        val item = ShoppingItem.dummyItem
        shoppingDetailRepository.saveShoppingItem(item)
        val result = shoppingItemDataSource.getAllShoppingItems().first()
        assertEquals(1, result.size)
        assertEquals(item.copy(id = 1), result[0])
    }

    @Test
    fun updateShoppingItem_expectedNoItem() = runBlocking {
        val item = ShoppingItem.dummyItem
        shoppingDetailRepository.saveShoppingItem(item)
        val result = shoppingItemDataSource.getAllShoppingItems().first()

        assertEquals(item.copy(id = 1), result[0])
        shoppingDetailRepository.updateShoppingItem(item.copy(id = 1, quantity = 10))
        val updateResult = shoppingItemDataSource.getAllShoppingItems().first()
        assertNotEquals(item.copy(id = 1), updateResult[0])
        assertEquals(item.copy(id = 1, quantity = 10), updateResult[0])
    }


    @After
    fun tearDown() {
        appDatabase.close()
    }
}