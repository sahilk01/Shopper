package com.example.shopper.model.datasource

import com.example.shopper.model.Filter
import com.example.shopper.model.FilterAction
import com.example.shopper.model.Sort
import com.example.shopper.model.Sorting
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.db.ShoppingItemDao
import com.example.shopper.util.logD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShoppingDbDataSource @Inject constructor(
    private val shoppingItemDao: ShoppingItemDao,
) : ShoppingItemDataSource {
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
            logD("Shopping ITEM while updating from datasource=> $shoppingItem")
            shoppingItemDao.update(shoppingItem = shoppingItem)
        }
    }

    override suspend fun searchShoppingList(
        searchQuery: String,
        selectedFilter: FilterAction?,
        selectedSorting: Sorting?
    ): Flow<List<ShoppingItem>> {
        return shoppingItemDao.search(searchQuery, selectedFilter?.ordinal, selectedSorting?.value)
    }

}