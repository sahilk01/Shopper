package com.example.shopper.model.repository.datasource

import com.example.shopper.model.FilterAction
import com.example.shopper.model.Sorting
import com.example.shopper.model.datasource.ShoppingItemDataSource
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.repository.FakeShoppingListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeShoppingDataSource: ShoppingItemDataSource {

    private val shoppingList = mutableListOf<ShoppingItem>()

    override suspend fun addShoppingItem(vararg shoppingItem: ShoppingItem) {
        shoppingItem.forEach {
            shoppingList.add(it)
        }
    }

    override suspend fun deleteShoppingItem(vararg shoppingItem: ShoppingItem) {
        shoppingItem.forEach {
            shoppingList.remove(it)
        }
    }

    override suspend fun getAllShoppingItems(): Flow<List<ShoppingItem>> {
        return flow { emit(shoppingList) }
    }

    override suspend fun getShoppingItem(id: Int): Flow<ShoppingItem> {
        return flow { shoppingList.find { it.id == id }?.let { emit(it) } }
    }

    override suspend fun updateShoppingItem(shoppingItem: ShoppingItem) {
        val foundItem = shoppingList.find { it.id == shoppingItem.id }
        shoppingList.remove(foundItem)
        shoppingList.add(shoppingItem)
    }

    override suspend fun searchShoppingList(
        searchQuery: String,
        selectedFilter: FilterAction?,
        selectedSorting: Sorting?
    ): Flow<List<ShoppingItem>> {
        return flow {
            val isBought = selectedFilter != FilterAction.Unbought
            val foundItems = shoppingList.filter {
                (it.name.contains(searchQuery) || it.description?.contains(
                    searchQuery
                ) == true) && it.isBought == isBought
            }

            if (foundItems.isNotEmpty()) {
                if (selectedSorting == Sorting.Ascending) {
                    emit(foundItems.sortedBy { it.name })
                } else {
                    emit(foundItems.sortedByDescending { it.name })
                }
            } else {
                emit(emptyList())
            }
        }
    }
}