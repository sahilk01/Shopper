package com.example.shopper.veiwModel

import com.example.shopper.model.FilterAction
import com.example.shopper.model.Sorting
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.repository.FakeShoppingDetailRepository
import com.example.shopper.model.repository.FakeShoppingListRepository
import com.example.shopper.model.repository.datasource.FakeShoppingDataSource
import com.example.shopper.model.repository.shoppingDetail.ShoppingItemDetailRepository
import com.example.shopper.model.repository.shoppingList.ShoppingListRepository
import com.example.shopper.util.MainDispatcherRule
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShoppingListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var listRepository: ShoppingListRepository
    private lateinit var detailRepository: ShoppingItemDetailRepository

    @Before
    fun setUp() {
        val dataSource = FakeShoppingDataSource()
        listRepository = FakeShoppingListRepository(dataSource)
        detailRepository = FakeShoppingDetailRepository(dataSource)
        viewModel = ShoppingListViewModel(listRepository, detailRepository)
    }

    @Test
    fun `fullList, get All Unbought items in Ascending order`() = runTest {
        val shoppingList = listOf(
            ShoppingItem(1, name = "Tomato", quantity = 2),
            ShoppingItem(2, name = "Onion", quantity = 4),
            ShoppingItem(3, name = "Rice", quantity = 5),
            ShoppingItem(4, name = "Cheese", quantity = 7),
        )
        shoppingList.forEach {
            detailRepository.saveShoppingItem(it)
        }
        viewModel.search("")
        assertEquals(shoppingList.size, viewModel.fullShoppingList.value.size)
    }

    @Test
    fun `search, get searched item with given filters and sorting`() = runTest {
        val item = ShoppingItem.dummyItem
        detailRepository.saveShoppingItem(item)
        val searchQuery = "Tomato"
        viewModel.search(searchQuery)
        assertEquals(1, viewModel.fullShoppingList.value.size)
    }

    @Test
    fun `search, search Onion within multiple items`() = runTest {
        val shoppingList = listOf(
            ShoppingItem(1, name = "Tomato", quantity = 2),
            ShoppingItem(2, name = "Onion", quantity = 4),
            ShoppingItem(3, name = "Rice", quantity = 5),
            ShoppingItem(4, name = "Cheese", quantity = 7),
        )
        shoppingList.forEach {
            detailRepository.saveShoppingItem(it)
        }
        viewModel.search("")
        assertEquals(4, viewModel.fullShoppingList.value.size)
        val searchQuery = "Onion"
        viewModel.search(searchQuery)
        assertEquals(searchQuery, viewModel.fullShoppingList.value[0].name)
    }

    @Test
    fun `searchUnbought, search unbought items`() = runTest {
        val shoppingList = listOf(
            ShoppingItem(1, name = "Tomato", quantity = 2, isBought = true),
            ShoppingItem(2, name = "Onion", quantity = 4, isBought = true),
            ShoppingItem(3, name = "Rice", quantity = 5, isBought = false),
            ShoppingItem(4, name = "Cheese", quantity = 7, isBought = false),
        )
        shoppingList.forEach {
            detailRepository.saveShoppingItem(it)
        }
        viewModel.applyFilter(FilterAction.Unbought)
        viewModel.fullShoppingList.value.forEach { foundShoppingItem ->
            assertEquals(false, !foundShoppingItem.isBought)
        }
    }

    @Test
    fun `searchBought, search bought items`() = runTest {
        val shoppingList = listOf(
            ShoppingItem(1, name = "Tomato", quantity = 2, isBought = true),
            ShoppingItem(2, name = "Onion", quantity = 4, isBought = true),
            ShoppingItem(3, name = "Rice", quantity = 5, isBought = false),
        )
        shoppingList.forEach {
            detailRepository.saveShoppingItem(it)
        }
        viewModel.applyFilter(FilterAction.Bought)
        viewModel.fullShoppingList.value.forEach { foundShoppingItem ->
            assertEquals(true, foundShoppingItem.isBought)
        }
    }
}