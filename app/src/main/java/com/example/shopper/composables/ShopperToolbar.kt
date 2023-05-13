package com.example.shopper.composables

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shopper.R
import com.example.shopper.model.Option
import com.example.shopper.ui.theme.White
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun ShopperToolbar(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.app_name),
    enableBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    navigator: DestinationsNavigator? = null,
    options: List<Option>? = null,
    content: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentPadding = PaddingValues(horizontal = 20.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (enableBackButton) {
                    BackButton {
                        navigator?.navigateUp()
                        onBackClick()
                    }
                }
                Text(text = title, fontWeight = FontWeight.Bold, color = White)
            }
            Row(
                modifier = Modifier
            ) {
                options?.forEach { option ->
                    IconButton(
                        onClick = {
                            option.onClick(option.optionType)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = option.icon),
                            contentDescription = "option icon",
                            tint = White
                        )
                    }
                }
            }
        }
        content()
    }
}