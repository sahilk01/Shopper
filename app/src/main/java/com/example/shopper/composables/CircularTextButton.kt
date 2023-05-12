package com.example.shopper.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shopper.ui.theme.White

@Composable
fun CircularTextButton(
    text: String? = null,
    backgroundColor: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .size(32.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        text?.let {
            Text(
                modifier = Modifier.clip(CircleShape),
                text = it, color = White
            )
        }
    }
}