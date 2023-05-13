@file:OptIn(ExperimentalMaterialApi::class)

package com.example.shopper.view.shoppingList

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shopper.R
import com.example.shopper.composables.*
import com.example.shopper.model.Option
import com.example.shopper.model.OptionType
import com.example.shopper.util.logD
import com.example.shopper.veiwModel.ShoppingListViewModel
import com.example.shopper.view.destinations.AddShoppingItemScreenDestination
import com.example.shopper.view.shoppingItemOptions.OptionsBottomSheet
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@RootNavGraph(start = true)
@Destination
@Composable
fun ShoppingListScreen(
    navigator: DestinationsNavigator,
    shoppingListViewModel: ShoppingListViewModel = hiltViewModel()
) {

    val coroutineScope = rememberCoroutineScope()

    val sheetState = configureSheetState(coroutineScope = coroutineScope)

    val shoppingList = shoppingListViewModel.fullShoppingList.collectAsState()
    val showEmptyLayout = shoppingListViewModel.showEmptyLayout.collectAsState()
    val filters = shoppingListViewModel.filter.collectAsState()
    val sortings = shoppingListViewModel.sortings.collectAsState()
    var showSearch by remember {
        mutableStateOf(false)
    }

    ShopperBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            OptionsBottomSheet(
                selectedShoppingItem = shoppingListViewModel.getSelectedShopping(),
                onEditClick = { shoppingItem ->
                    coroutineScope.launch { sheetState.hide() }
                    shoppingListViewModel.clearSearch()
                    navigator.navigate(AddShoppingItemScreenDestination(shoppingItem = shoppingItem))
                },
                onDeleteClick = { shoppingItem ->
                    shoppingListViewModel.deleteShoppingItem(shoppingItem)
                    coroutineScope.launch { sheetState.hide() }
                }
            )
        }) {
        Scaffold(
            topBar = {
                if (showSearch) {
                    SearchField(
                        onSearch = { searchQuery ->
                        logD("Search Item => $searchQuery")
                            shoppingListViewModel.search(searchQuery)
                        },
                        onBackPress = {
                            shoppingListViewModel.clearSearch()
                            showSearch = false
                        }
                    )

                    BackHandler(showSearch) {
                        shoppingListViewModel.clearSearch()
                        showSearch = false
                    }
                } else {
                    ShopperToolbar(options = Option.getShoppingListOptions { optionType ->
                        when(optionType) {
                            OptionType.Search -> {
                                showSearch = true
                            }
                        }
                    })
                }
            },
            floatingActionButton = {
                ShopperFAB(icon = R.drawable.add,
                    onClick = {
                        shoppingListViewModel.clearSearch()
                        navigator.navigate(AddShoppingItemScreenDestination())
                    })
            },
            ) {
            Column(
                modifier = Modifier.padding(it)
            ) {

                Row {
                        FilterOptions(
                            filters = filters.value,
                            onFilterSelected = { selectedFilter ->
                                shoppingListViewModel.applyFilter(selectedFilter.filterType)
                            })

                        SortingOptions(sortings = sortings.value, onSortSelected = { selectedSort ->
                            shoppingListViewModel.applySort(selectedSort.type)
                        })
                }
                Spacer(modifier = Modifier.padding(top = 16.dp))
                ShoppingList(
                    shoppingList = shoppingList.value,
                    showEmptyLayout = showEmptyLayout.value,
                    onOptionsClick = { selectedShoppingItem ->
                        shoppingListViewModel.setSelectedShopping(selectedShoppingItem)
                        coroutineScope.launch {
                            sheetState.show()
                        }
                    },
                    onShoppingItemClick = { selectedShoppingItem ->
                        shoppingListViewModel.clearSearch()
                        navigator.navigate(AddShoppingItemScreenDestination(shoppingItem = selectedShoppingItem))
                    },
                    onBoughtClick = { shoppingItem ->
                        shoppingListViewModel.updateBuyState(shoppingItem)
                    }
                )
            }
        }
    }

}

