package com.example.shopper.model.db

import androidx.room.*
import com.example.shopper.model.db.entity.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Insert
    suspend fun insert(shoppingItem: ShoppingItem)

    @Insert
    suspend fun insert(vararg shoppingItem: ShoppingItem)

    @Delete
    suspend fun delete(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteAll(vararg shoppingItem: ShoppingItem)

    @Query("SELECT * FROM ${ShoppingItem.TABLE_NAME}")
    fun getAll(): Flow<List<ShoppingItem>>

    @Query("SELECT * FROM ${ShoppingItem.TABLE_NAME} WHERE id=:id")
    fun getByID(id: Int): Flow<ShoppingItem>

    @Update
    suspend fun update(shoppingItem: ShoppingItem)
}