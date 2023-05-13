package com.example.shopper.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorsPalette(
    val textFieldBg: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
)

val LocalShopperColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val OnLightShopperColorsPalette = CustomColorsPalette(
    textFieldBg = SuperLightGray,
    textColor = Color.Black
)

val OnDarkShopperColorsPalette = CustomColorsPalette(
    textFieldBg = DarkGray,
    textColor = White
)
