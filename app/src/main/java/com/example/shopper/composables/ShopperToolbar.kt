package com.example.shopper.composables

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shopper.R
import com.example.shopper.ui.theme.White
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun ShopperToolbar(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.app_name),
    enableBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    navigator: DestinationsNavigator? = null,
    content: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        if (enableBackButton) {
            Icon(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable {
                        navigator?.navigateUp()
                        onBackClick()
                    }
                    .padding(6.dp),
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = stringResource(R.string.back_button),
                tint = Color.White
            )
        }
        Text(text = title, fontWeight = FontWeight.Bold, color = White)
        content()
    }
}