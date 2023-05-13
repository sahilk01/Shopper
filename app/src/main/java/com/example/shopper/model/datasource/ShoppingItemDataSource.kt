package com.example.shopper.model.datasource

import com.example.shopper.model.FilterAction
import com.example.shopper.model.Sorting
import com.example.shopper.model.db.entity.ShoppingItem
import kotlinx.coroutines.flow.Flow

interface ShoppingItemDataSource {
    /**
    Add one or more Shopping Items.
     */

    suspend fun addShoppingItem(vararg shoppingItem: ShoppingItem)

    /**
    Delete one or more shopping Items.
     */

    suspend fun deleteShoppingItem(vararg shoppingItem: ShoppingItem)

    /**
    Get all the All the Shopping Items.
     */

    suspend fun getAllShoppingItems(): Flow<List<ShoppingItem>>

    /**
    Get Shopping Item by ID.
     */

    suspend fun getShoppingItem(id: Int): Flow<ShoppingItem>

    /**
    Update Shopping Item.
     */

    suspend fun updateShoppingItem(shoppingItem: ShoppingItem)


    suspend fun searchShoppingList(searchQuery: String, selectedFilter: FilterAction? = null, selectedSorting: Sorting? = null): Flow<List<ShoppingItem>>
}