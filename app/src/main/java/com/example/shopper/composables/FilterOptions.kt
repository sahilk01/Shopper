@file:OptIn(ExperimentalMaterialApi::class)

package com.example.shopper.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopper.model.Filter
import com.example.shopper.model.Sort

@Composable
fun FilterOptions(
    filters: List<Filter> = Filter.getFilters(),
    onFilterSelected: (Filter) -> Unit,
) {

    LazyRow {
        items(filters) { filter ->

            FilterChip(
                modifier = Modifier.padding(all = 5.dp),
                selected = filter.isSelected,
                onClick = {
                    onFilterSelected(filter.copy(isSelected = true))
                }) {
                Text(text = filter.filterType.name, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun SortingOptions(
    sortings: List<Sort>,
    onSortSelected: (Sort) -> Unit,
) {

    LazyRow {
        items(sortings) { sorting ->

            FilterChip(
                modifier = Modifier.padding(all = 5.dp),
                selected = sorting.isSelected,
                onClick = {
                    onSortSelected(sorting)
                }) {
                Text(text = sorting.type.name, fontSize = 12.sp)
            }
        }
    }
}