package com.example.shopper.veiwModel

import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.repository.FakeShoppingDetailRepository
import com.example.shopper.model.repository.FakeShoppingListRepository
import com.example.shopper.model.repository.datasource.FakeShoppingDataSource
import com.example.shopper.model.repository.shoppingDetail.ShoppingItemDetailRepository
import com.example.shopper.model.repository.shoppingList.ShoppingListRepository
import com.example.shopper.util.MainDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShoppingItemDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ShoppingItemDetailViewModel
    private lateinit var listRepository: ShoppingListRepository
    private lateinit var detailRepository: ShoppingItemDetailRepository

    @Before
    fun setUp() {
        val dataSource = FakeShoppingDataSource()
        listRepository = FakeShoppingListRepository(dataSource)
        detailRepository = FakeShoppingDetailRepository(dataSource)
        viewModel = ShoppingItemDetailViewModel(detailRepository)
    }

    @Test
    fun `saveItem, save a tomato into shopping list`() = runTest {
        val item = ShoppingItem.dummyItem
        viewModel.setShoppingItem(item)
        viewModel.saveShoppingItem()
        val result = listRepository.getAllShoppingItems()?.first()
        assertEquals(1, result?.size)
    }

    @Test
    fun `saveMultipleItems, save multiple items into shopping list`() = runTest {

        val items = ShoppingItem.dummyList
        items.forEach {
            viewModel.setShoppingItem(it)
            viewModel.saveShoppingItem()
        }
        val result = listRepository.getAllShoppingItems()?.first()
        assertEquals(items.size, result?.size)
    }

    @Test
    fun `saveItemWithoutDescription, save item without description`() = runTest {
        val item = ShoppingItem.dummyItem.copy(description = null)
        viewModel.setShoppingItem(item)
        viewModel.saveShoppingItem()
        val result = listRepository.getAllShoppingItems()?.first()
        assertEquals(item, result?.get(0))
    }

    @Test
    fun `updateOnion, update Onion with Quantity from 1 to 4`() = runTest {
        val item = ShoppingItem.dummyItem.copy(quantity = 1)
        viewModel.setShoppingItem(item)
        viewModel.saveShoppingItem()
        val result = listRepository.getAllShoppingItems()?.first()
        assertEquals(item, result?.get(0))

        val updatedQuantity = 4
        viewModel.setShoppingItem(result?.get(0)?.copy(quantity = updatedQuantity))
        viewModel.updateShoppingItem(result?.get(0)?.copy(quantity = updatedQuantity)!!)
        val updateResult = listRepository.getAllShoppingItems()?.first()
        assertEquals(updatedQuantity, updateResult?.get(0)?.quantity)
    }
}