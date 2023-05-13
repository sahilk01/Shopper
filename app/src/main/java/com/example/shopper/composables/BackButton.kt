package com.example.shopper.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shopper.R

@Composable
fun BackButton(
    tint: Color = Color.White,
    onClick: () -> Unit
) {
    Icon(
        modifier = Modifier
            .padding(end = 10.dp)
            .size(32.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .padding(6.dp),
        painter = painterResource(id = R.drawable.arrow_back),
        contentDescription = stringResource(R.string.back_button),
        tint = tint)
}