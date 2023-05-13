package com.example.shopper.model

import androidx.annotation.DrawableRes
import com.example.shopper.R

data class Option(
    @DrawableRes
    val icon: Int,
    val optionType: String,
    val onClick: (String) -> Unit,
) {
    companion object {
        fun getShoppingListOptions(onSearchClick: (String) -> Unit): List<Option> = listOf(
            Option(
                icon = R.drawable.search,
                optionType = OptionType.Search,
                onClick = onSearchClick
            )
        )
    }
}

object OptionType {
    const val Search = "search"
}