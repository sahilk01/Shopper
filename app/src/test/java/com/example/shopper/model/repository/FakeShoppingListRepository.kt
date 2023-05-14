package com.example.shopper.model.repository

import com.example.shopper.model.FilterAction
import com.example.shopper.model.Sorting
import com.example.shopper.model.datasource.ShoppingItemDataSource
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.repository.shoppingList.ShoppingListRepository
import kotlinx.coroutines.flow.Flow

class FakeShoppingListRepository(
    private val dataSource: ShoppingItemDataSource
) : ShoppingListRepository {

    override suspend fun deleteShoppingItem(vararg shoppingItem: ShoppingItem) {
        shoppingItem.forEach {
            dataSource.deleteShoppingItem(it)
        }
    }

    override suspend fun getAllShoppingItems(): Flow<List<ShoppingItem>>? {
       return dataSource.getAllShoppingItems()
    }

    override suspend fun getShoppingItem(id: Int): Flow<ShoppingItem>? {
        return dataSource.getShoppingItem(id)
    }

    override suspend fun searchShoppingList(
        searchQuery: String,
        selectedFilter: FilterAction?,
        selectedSorting: Sorting?
    ): Flow<List<ShoppingItem>>? {
        return dataSource.searchShoppingList(searchQuery, selectedFilter, selectedSorting)
    }
}