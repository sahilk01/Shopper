package com.example.shopper.model

enum class Sorting(val value: Int) {
    Ascending(1),
    Descending(2),
}

data class Sort(
    val isSelected: Boolean,
    val type: Sorting
) {
    companion object {
        fun getSortings() = listOf(
            Sort(
                isSelected = true,
                type = Sorting.Ascending
            ),
            Sort(
                isSelected = false,
                type = Sorting.Descending
            ),
        )
    }
}