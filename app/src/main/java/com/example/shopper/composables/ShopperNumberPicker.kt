package com.example.shopper.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShopperNumberPicker(
    modifier: Modifier = Modifier,
    count: Int,
    onDecreaseClick: () -> Unit = {},
    onIncreaseClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        CircularTextButton(
            text = "-",
            onClick = {
                if (count > 1) {
                    onDecreaseClick()
                }
            }
        )

        Spacer(modifier = Modifier.padding(start = 12.dp))
        AnimatedCounter(
            modifier = modifier,
            count = count
        )
        Spacer(modifier = Modifier.padding(start = 12.dp))
        CircularTextButton(
            text = "+",
            onClick = onIncreaseClick
        )
    }
}

