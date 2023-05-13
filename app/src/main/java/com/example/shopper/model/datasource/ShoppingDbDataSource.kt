package com.example.shopper.model.datasource

import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.db.ShoppingItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShoppingDbDataSource @Inject constructor(
    private val shoppingItemDao: ShoppingItemDao,
): ShoppingItemDataSource {
    override suspend fun addShoppingItem(vararg shoppingItem: ShoppingItem) {
        withContext(Dispatchers.IO) {
            shoppingItemDao.insert(shoppingItem = shoppingItem)
        }
    }

    override suspend fun deleteShoppingItem(vararg shoppingItem: ShoppingItem) {
        withContext(Dispatchers.IO) {
            shoppingItemDao.deleteAll(shoppingItem = shoppingItem)
        }
    }

    override suspend fun getAllShoppingItems(): Flow<List<ShoppingItem>> {
        return shoppingItemDao.getAll()
    }

    override suspend fun getShoppingItem(id: Int): Flow<ShoppingItem> {
        return shoppingItemDao.getByID(id = id)
    }

    override suspend fun updateShoppingItem(shoppingItem: ShoppingItem) {
        withContext(Dispatchers.IO) {
            shoppingItemDao.update(shoppingItem = shoppingItem)
        }
    }
}