package com.example.shopper.model.repository

import com.example.shopper.model.datasource.ShoppingItemDataSource
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.repository.shoppingDetail.ShoppingItemDetailRepository

class FakeShoppingDetailRepository(
    val dataSource: ShoppingItemDataSource
): ShoppingItemDetailRepository {

    override suspend fun saveShoppingItem(shoppingItem: ShoppingItem) {
        dataSource.addShoppingItem(shoppingItem)
    }

    override suspend fun updateShoppingItem(shoppingItem: ShoppingItem) {
        dataSource.updateShoppingItem(shoppingItem)
    }
}