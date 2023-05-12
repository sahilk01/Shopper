package com.example.shopper.model.repository.shoppingList

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
}