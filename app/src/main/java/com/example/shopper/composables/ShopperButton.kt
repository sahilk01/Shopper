package com.example.shopper.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ShopperButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    contentPadding: PaddingValues = PaddingValues(vertical = 12.dp),
    icon: Painter? = null,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(32.dp),
        onClick = onClick,
        contentPadding = contentPadding,
        enabled = isEnabled,
    ) {
        icon?.let {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = icon,
                contentDescription = "Button Icon"
            )
            Spacer(modifier = Modifier.padding(start = 10.dp))
        }
        
        
        text?.let {
            Text(text = it)
        }
    }
}

@Composable
fun ShopperOutlinedButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    contentPadding: PaddingValues = PaddingValues(vertical = 12.dp),
    icon: Painter? = null,
    onClick: () -> Unit = {},
) {
    OutlinedButton(
        modifier = modifier,
        shape = RoundedCornerShape(32.dp),
        onClick = onClick,
        contentPadding = contentPadding,
    ) {
        icon?.let {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = icon,
                contentDescription = "Button Icon"
            )
            Spacer(modifier = Modifier.padding(start = 10.dp))
        }


        text?.let {
            Text(text = it)
        }
    }
}