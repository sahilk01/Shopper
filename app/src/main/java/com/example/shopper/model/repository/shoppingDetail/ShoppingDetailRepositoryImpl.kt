package com.example.shopper.model.repository.shoppingDetail

import com.example.shopper.model.datasource.ShoppingItemDataSource
import com.example.shopper.model.db.entity.ShoppingItem
import javax.inject.Inject

class ShoppingDetailRepositoryImpl @Inject constructor(
    private val dataSources: Array<ShoppingItemDataSource>
) : ShoppingItemDetailRepository {
    override suspend fun saveShoppingItem(shoppingItem: ShoppingItem) {
        dataSources.forEach { shoppingItemDataSource ->
            shoppingItemDataSource.addShoppingItem(shoppingItem)
        }
    }

    override suspend fun updateShoppingItem(shoppingItem: ShoppingItem) {
        dataSources.forEach { shoppingItemDataSource ->
            shoppingItemDataSource.updateShoppingItem(shoppingItem)
        }
    }
}