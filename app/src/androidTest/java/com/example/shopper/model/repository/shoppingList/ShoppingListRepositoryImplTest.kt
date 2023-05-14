package com.example.shopper.model.repository.shoppingList

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.shopper.model.FilterAction
import com.example.shopper.model.Sorting
import com.example.shopper.model.datasource.ShoppingDbDataSource
import com.example.shopper.model.datasource.ShoppingItemDataSource
import com.example.shopper.model.db.AppDatabase
import com.example.shopper.model.db.ShoppingItemDao
import com.example.shopper.model.db.entity.ShoppingItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ShoppingListRepositoryTest {

    lateinit var appDatabase: AppDatabase
    lateinit var shoppingItemDao: ShoppingItemDao
    private lateinit var shoppingItemDataSource: ShoppingItemDataSource
    lateinit var shoppingListRepository: ShoppingListRepository

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        shoppingItemDao = appDatabase.shoppingItemDao()
        shoppingItemDataSource = ShoppingDbDataSource(shoppingItemDao)
        shoppingListRepository = ShoppingListRepositoryImpl(arrayOf(shoppingItemDataSource))
    }

    @Test
    fun getAllShoppingItems() = runBlocking {
        val item = ShoppingItem.dummyItem

        val result = shoppingListRepository.getAllShoppingItems()?.first()
        assertEquals(0, result?.size)

        shoppingItemDataSource.addShoppingItem(item)

        val resultAfterInsert = shoppingListRepository.getAllShoppingItems()?.first()

        assertEquals(1, resultAfterInsert?.size)
        assertEquals(item.copy(id = 1), resultAfterInsert!![0])
    }

    @Test
    fun deleteShoppingItem() = runBlocking {
        val item = ShoppingItem.dummyItem
        shoppingItemDataSource.addShoppingItem(item)
        val result = shoppingListRepository.getAllShoppingItems()?.first()
        assertEquals(1, result?.size)
        shoppingListRepository.deleteShoppingItem(item.copy(id = 1))

        val resultAfterDelete = shoppingListRepository.getAllShoppingItems()?.first()

        assertEquals(0, resultAfterDelete?.size)
    }

    @Test
    fun getShoppingItem() = runBlocking {
        val item = ShoppingItem.dummyItem
        shoppingItemDataSource.addShoppingItem(item)
        val result = shoppingListRepository.getShoppingItem(id = 1)?.first()
        assertEquals(1, result?.id)
        assertEquals(item.copy(1), result)
    }

    @Test
    fun searchShoppingItem_expectedTwoItem(): Unit = runBlocking {
        val tomato = ShoppingItem(name = "Tomato", description = "Red color Vegetable", quantity = 2, isBought = true)
        val onion = ShoppingItem(name = "Onion", quantity = 1)
        val rice = ShoppingItem(name = "rice", quantity = 2)
        shoppingItemDataSource.addShoppingItem(tomato, onion, rice)
        val result = shoppingItemDataSource.getAllShoppingItems().first()
        assertEquals(3, result.size)

        val searchQuery = "Onion"

        val searchResult = shoppingListRepository.searchShoppingList(searchQuery,
            FilterAction.Unbought,
            Sorting.Ascending
        )?.first()

        assertEquals(1, searchResult?.size)

        searchResult?.forEach {
            assertEquals(false, it.isBought)
            assertEquals(searchQuery, it.name)
        }
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }
}