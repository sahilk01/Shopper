package com.example.shopper.model.db

import androidx.room.*
import com.example.shopper.model.FilterAction
import com.example.shopper.model.Sort
import com.example.shopper.model.db.entity.ShoppingItem
import kotlinx.coroutines.flow.Flow
import java.util.logging.Filter

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

    @Query(
        "SELECT * FROM ${ShoppingItem.TABLE_NAME} WHERE name LIKE '%' || :searchQuery || '%' " +
                "AND is_bought=:filter " +
                "OR description LIKE '%' || :searchQuery || '%' " +
                "AND is_bought=:filter ORDER BY " +
                "CASE WHEN :sort = 1 THEN name END ASC, " +
                "CASE WHEN :sort = 2 THEN name END DESC"
    )
    fun search(searchQuery: String, filter: Int?, sort: Int?): Flow<List<ShoppingItem>>

    @Query("SELECT * FROM ${ShoppingItem.TABLE_NAME} WHERE id=:id")
    fun getByID(id: Int): Flow<ShoppingItem>

    @Update
    suspend fun update(shoppingItem: ShoppingItem)
}