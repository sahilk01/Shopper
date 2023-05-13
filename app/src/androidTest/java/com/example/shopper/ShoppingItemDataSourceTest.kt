package com.example.shopper

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
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ShoppingItemDataSourceTest {

    lateinit var appDatabase: AppDatabase
    lateinit var shoppingItemDao: ShoppingItemDao
    lateinit var shoppingItemDataSource: ShoppingItemDataSource

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        shoppingItemDao = appDatabase.shoppingItemDao()
        shoppingItemDataSource = ShoppingDbDataSource(shoppingItemDao)
    }


    @Test
    fun insertShoppingItem_expectedSingleItem() = runBlocking {
        val item = ShoppingItem(name = "Tomato", description = "Red color Vegetable", quantity = 2)

        shoppingItemDataSource.addShoppingItem(item)

        val result = shoppingItemDataSource.getAllShoppingItems().first()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals(item.copy(id = 1), result[0])
    }

    @Test
    fun insertThreeItem_expectedThreeItem() = runBlocking {
        val tomato = ShoppingItem(name = "Tomato", description = "Red color Vegetable", quantity = 2)
        val onion = ShoppingItem(name = "Onion", quantity = 1)
        val rice = ShoppingItem(name = "Rice", quantity = 3)

        shoppingItemDataSource.addShoppingItem(tomato, onion, rice)

        val result = shoppingItemDataSource.getAllShoppingItems().first()

        Assert.assertEquals(3, result.size)
        Assert.assertEquals(tomato.copy(id = 1), result[0])
        Assert.assertEquals(onion.copy(id = 2), result[1])
        Assert.assertEquals(rice.copy(id = 3), result[2])
    }

    @Test
    fun deleteShoppingItem_expectedNoItem() = runBlocking {
        val item = ShoppingItem(name = "Tomato", description = "Red color Vegetable", quantity = 2)
        shoppingItemDataSource.addShoppingItem(item)
        shoppingItemDataSource.deleteShoppingItem(item.copy(id = 1))

        val result = shoppingItemDataSource.getAllShoppingItems().first()

        Assert.assertEquals(0, result.size)
    }

    @Test
    fun updateShoppingItem_expectedNoItem() = runBlocking {
        val item = ShoppingItem(name = "Tomato", description = "Red color Vegetable", quantity = 2)
        shoppingItemDataSource.addShoppingItem(item)
        val result = shoppingItemDataSource.getAllShoppingItems().first()

        Assert.assertEquals(item.copy(id = 1), result[0])
        shoppingItemDataSource.updateShoppingItem(item.copy(id = 1, quantity = 3))
        val updateResult = shoppingItemDataSource.getAllShoppingItems().first()
        Assert.assertNotEquals(item.copy(id = 1), updateResult[0])
        Assert.assertEquals(item.copy(id = 1, quantity = 3), updateResult[0])
    }

    @Test
    fun filterShoppingItem_expectedTwoItem() = runBlocking {
        val tomato = ShoppingItem(name = "Tomato", description = "Red color Vegetable", quantity = 2, isBought = true)
        val onion = ShoppingItem(name = "Onion", quantity = 1)
        val rice = ShoppingItem(name = "rice", quantity = 2)
        shoppingItemDataSource.addShoppingItem(tomato, onion, rice)
        val result = shoppingItemDataSource.getAllShoppingItems().first()
        Assert.assertEquals(3, result.size)

        val searchResult = shoppingItemDataSource.searchShoppingList("", FilterAction.Unbought, Sorting.Ascending).first()

        Assert.assertEquals(2, searchResult.size)

        searchResult.forEach {
            Assert.assertEquals(false, it.isBought)
        }
    }


    @After
    fun tearDown() {
        appDatabase.close()
    }
}