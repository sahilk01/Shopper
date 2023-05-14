package com.example.shopper.veiwModel

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.shopper.model.datasource.ShoppingDbDataSource
import com.example.shopper.model.datasource.ShoppingItemDataSource
import com.example.shopper.model.db.AppDatabase
import com.example.shopper.model.db.ShoppingItemDao
import com.example.shopper.model.repository.shoppingDetail.ShoppingDetailRepositoryImpl
import com.example.shopper.model.repository.shoppingDetail.ShoppingItemDetailRepository
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class ShoppingListViewModelTest {

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

    @After
    fun tearDown() {
        appDatabase.close()
    }
}