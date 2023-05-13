package com.example.shopper.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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