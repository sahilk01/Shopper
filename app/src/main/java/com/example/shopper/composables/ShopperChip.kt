package com.example.shopper.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShopperChip(
    isSelected: Boolean = false,
    text: String,
    onClick: (Boolean) -> Unit
) {
    val selectedChipConfig = ChipConfig(accentColor = MaterialTheme.colors.primary)
    val unSelectedChipConfig = ChipConfig(accentColor = Color.LightGray)
    val chipConfig = if (isSelected) selectedChipConfig else unSelectedChipConfig

    Text(
        modifier = Modifier
            .padding(all = 6.dp)
            .border(width = 1.dp, chipConfig.accentColor, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp),
        text = text,
        overflow = TextOverflow.Ellipsis,
        fontSize = 12.sp,
        color = chipConfig.accentColor
    )
}

data class ChipConfig(
    val accentColor: Color
)