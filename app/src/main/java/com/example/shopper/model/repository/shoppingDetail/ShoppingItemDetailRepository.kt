package com.example.shopper.model.repository.shoppingDetail

import com.example.shopper.model.db.entity.ShoppingItem

interface ShoppingItemDetailRepository {

    /**
    Save shopping Item.
     */

    suspend fun saveShoppingItem(shoppingItem: ShoppingItem)

    /**
    Update shopping Item.
     */

    suspend fun updateShoppingItem(shoppingItem: ShoppingItem)
}