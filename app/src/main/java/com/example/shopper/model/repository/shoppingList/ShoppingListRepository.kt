package com.example.shopper.model.repository.shoppingList

import com.example.shopper.model.FilterAction
import com.example.shopper.model.Sorting
import com.example.shopper.model.db.entity.ShoppingItem
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {
    /**
    Delete one or more shopping Items.
     */

    suspend fun deleteShoppingItem(vararg shoppingItem: ShoppingItem)

    /**
      Get all the All the Shopping Items.
    */

    suspend fun getAllShoppingItems():  Flow<List<ShoppingItem>>?

    /**
      Get Shopping Item by ID.
    */

    suspend fun getShoppingItem(id: Int): Flow<ShoppingItem>?

    /**
    Search in Shopping List with keeping already Applied Filter/Sort.
     */

    suspend fun searchShoppingList(searchQuery: String, selectedFilter: FilterAction? = null, selectedSorting: Sorting? = null): Flow<List<ShoppingItem>>?
}