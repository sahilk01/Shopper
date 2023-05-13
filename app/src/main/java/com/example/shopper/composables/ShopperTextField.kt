package com.example.shopper.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.shopper.R
import com.example.shopper.ui.theme.SuperLightGray

@Composable
fun ShopperTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    placeholder: String,
    onValueChange: (TextFieldValue) -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        value = value,
        onValueChange = { name ->
            onValueChange(name)
        },
        placeholder = {
            Text(
                text = placeholder
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = SuperLightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Color.Black
        ),
        shape = RoundedCornerShape(8.dp),
    )
}