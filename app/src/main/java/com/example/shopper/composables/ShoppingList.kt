package com.example.shopper.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import com.example.shopper.model.db.entity.ShoppingItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun ShoppingList(
    shoppingList: List<ShoppingItem> = ShoppingItem.dummyList,
    showEmptyLayout: Boolean,
    onShoppingItemClick: (ShoppingItem) -> Unit = {},
    onOptionsClick: (ShoppingItem) -> Unit = {},
    onBoughtClick: (ShoppingItem) -> Unit = {}
) {
//    var showNoDataLayout by remember { mutableStateOf(false) }
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

//    LaunchedEffect(key1 = "key1", block = {
//            delay(1.seconds)
//            if (shoppingList.isEmpty()) {
//                showNoDataLayout = true
//            }
//    })

    if (showEmptyLayout) {
        NoDataLayout()
    }
}