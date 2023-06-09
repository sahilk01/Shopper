package com.example.shopper.model.repository.shoppingList

import com.example.shopper.model.Filter
import com.example.shopper.model.FilterAction
import com.example.shopper.model.Sort
import com.example.shopper.model.Sorting
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.datasource.ShoppingDbDataSource
import com.example.shopper.model.datasource.ShoppingItemDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingListRepositoryImpl @Inject constructor(
    private val dataSources: Array<ShoppingItemDataSource>
): ShoppingListRepository {
    override suspend fun deleteShoppingItem(vararg shoppingItem: ShoppingItem) {
        dataSources.forEach { shoppingItemDataSource ->
            shoppingItemDataSource.deleteShoppingItem(shoppingItem = shoppingItem)
        }
    }

    override suspend fun getAllShoppingItems(): Flow<List<ShoppingItem>>? {
        dataSources.forEach { shoppingItemDataSource ->
            if (shoppingItemDataSource is ShoppingDbDataSource) {
                return shoppingItemDataSource.getAllShoppingItems()
            } else {
                shoppingItemDataSource.getAllShoppingItems()
            }
        }
        return null
    }

    override suspend fun getShoppingItem(id: Int): Flow<ShoppingItem>? {
        dataSources.forEach { shoppingItemDataSource ->
            if (shoppingItemDataSource is ShoppingDbDataSource) {
                return shoppingItemDataSource.getShoppingItem(id)
            } else {
                shoppingItemDataSource.getShoppingItem(id)
            }
        }
        return null
    }

    override suspend fun searchShoppingList(
        searchQuery: String,
        selectedFilter: FilterAction?,
        selectedSorting: Sorting?
    ): Flow<List<ShoppingItem>>? {
        dataSources.forEach { shoppingItemDataSource ->
            if (shoppingItemDataSource is ShoppingDbDataSource) {
                return shoppingItemDataSource.searchShoppingList(searchQuery, selectedFilter, selectedSorting)
            } else {
                shoppingItemDataSource.searchShoppingList(searchQuery, selectedFilter, selectedSorting)
            }
        }
        return null
    }
}