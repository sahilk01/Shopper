package com.example.shopper.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.shopper.model.db.entity.ShoppingItem

@Composable
fun ShoppingList(
    shoppingList: List<ShoppingItem> = ShoppingItem.dummyList,
    onShoppingItemClick: (ShoppingItem) -> Unit = {},
    onOptionsClick: (ShoppingItem) -> Unit = {},
    onBoughtClick: (ShoppingItem) -> Unit = {}
) {
    LazyColumn {
        items(shoppingList) { shoppingItem ->
            ShoppingItemCard(
                shoppingItem = shoppingItem,
                onShoppingItemClick = onShoppingItemClick,
                onOptionsClick = onOptionsClick,
                onBoughtClick = onBoughtClick
            )
        }
    }
}