package com.example.shopper.model

data class Filter(
    val isSelected: Boolean,
    val filterType: FilterAction,
) {
    companion object {
        fun getFilters() = listOf(
            Filter(
                isSelected = true,
                filterType = FilterAction.Unbought
            ),
            Filter(
                isSelected = false,
                filterType = FilterAction.Bought
            ),
//            Filter(
//                isSelected = false,
//                filterType = FilterAction.All
//            ),
        )
    }
}

enum class FilterAction {
    Unbought,
    Bought,
//    All,
}