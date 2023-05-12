package com.example.shopper.composables

import androidx.annotation.DrawableRes
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun ShopperFAB(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    iconTint: Color = Color.White,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(
            painter = painterResource(id = icon),
            tint = iconTint,
            contentDescription = "Floating Action Button"
        )
        content()
    }
}