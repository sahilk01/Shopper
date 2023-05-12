@file:OptIn(ExperimentalMaterialApi::class)

package com.example.shopper.composables

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ShopperBottomSheetLayout(
    modifier: Modifier = Modifier,
    sheetState: ModalBottomSheetState,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        scrimColor = Color.Black.copy(alpha = 0.25f),
        sheetContent = sheetContent,
        content = content
    )
}

@Composable
fun configureSheetState(coroutineScope: CoroutineScope): ModalBottomSheetState {
    val sheetState = defaultSheetState()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    return sheetState
}

@Composable
fun defaultSheetState() = rememberModalBottomSheetState(
    initialValue = ModalBottomSheetValue.Hidden,
    confirmValueChange = { it != ModalBottomSheetValue.Expanded }
)