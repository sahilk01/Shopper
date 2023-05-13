package com.example.shopper.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkPurple,
    primaryVariant = DarkBlue,
    secondary = OrangishYellow,
    secondaryVariant = Pink
)

private val LightColorPalette = lightColors(
    primary = DarkPurple,
    primaryVariant = DarkBlue,
    secondary = OrangishYellow,
    secondaryVariant = Pink

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)
@Composable
fun ShopperTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val shopperColorsPalette =
        if (darkTheme) OnDarkShopperColorsPalette
        else OnLightShopperColorsPalette
    
    CompositionLocalProvider(LocalShopperColorsPalette provides shopperColorsPalette) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }

}