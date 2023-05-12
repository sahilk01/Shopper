package com.example.shopper.model.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ShoppingItem.TABLE_NAME)
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("quantity")
    val quantity: Int,

    @ColumnInfo("description")
    val description: String? = null,

    @ColumnInfo("is_bought")
    val isBought: Boolean = false,
) {
    companion object {
        const val TABLE_NAME = "shopping_item"

        val dummyList get() = listOf(
            ShoppingItem(
                name = "Tomato",
                quantity = 2,
                description = "A vegetable to make great salad"
            ),
            ShoppingItem(
                name = "Tomato",
                quantity = 2,
            ),
            ShoppingItem(
                name = "Tomato",
                quantity = 2,
            ),
            ShoppingItem(
                name = "Tomato",
                quantity = 2,
                description = "A vegetable to make great salad"
            ),
        )
        val dummyItem get() = ShoppingItem(
            name = "Tomato",
            quantity = 2,
            description = "A vegetable to make great salad"
        )
    }
}
