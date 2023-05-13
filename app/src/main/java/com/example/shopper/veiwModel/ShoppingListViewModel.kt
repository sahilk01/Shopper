package com.example.shopper.veiwModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.model.Filter
import com.example.shopper.model.FilterAction
import com.example.shopper.model.Sort
import com.example.shopper.model.Sorting
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.repository.shoppingDetail.ShoppingItemDetailRepository
import com.example.shopper.model.repository.shoppingList.ShoppingListRepository
import com.example.shopper.util.logD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository,
    private val shoppingItemDetailRepository: ShoppingItemDetailRepository
) : ViewModel() {

    private val _fullShoppingList = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val fullShoppingList get() = _fullShoppingList.asStateFlow()

    private var selectedShoppingItem: ShoppingItem? = null
    var selectedFilter: FilterAction = FilterAction.Unbought
    private var dataJob: Job? = null
    private var selectedSort: Sorting = Sorting.Ascending

    private val _filters = MutableStateFlow(Filter.getFilters())
    val filter get() = _filters.asStateFlow()

    private val _sortings = MutableStateFlow(Sort.getSortings())
    val sortings get() = _sortings.asStateFlow()

    private val _showEmptyLayout = MutableStateFlow(false)
    val showEmptyLayout get() = _showEmptyLayout.asStateFlow()

    private var searchQuery: String? = null

    fun setSelectedShopping(selectedShoppingItem: ShoppingItem) {
        this.selectedShoppingItem = selectedShoppingItem
    }

    fun getSelectedShopping() = selectedShoppingItem

    init {
        applyFilter(selectedFilter, selectedSort, updateInUi = false)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            shoppingListRepository.deleteShoppingItem(shoppingItem)
        }
    }

    fun updateBuyState(shoppingItem: ShoppingItem) {
        if (shoppingItem.isBought) {
            unBought(shoppingItem)
        } else {
            bought(shoppingItem)
        }
    }

    private fun bought(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            shoppingItemDetailRepository.updateShoppingItem(
                shoppingItem.copy(isBought = true)
            )
        }
    }

    private fun unBought(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            shoppingItemDetailRepository.updateShoppingItem(
                shoppingItem.copy(isBought = false)
            )
        }
    }

    fun applyFilter(selectedFilter: FilterAction, sorting: Sorting = selectedSort, updateInUi: Boolean = true) {
        if(this.selectedFilter == selectedFilter && updateInUi) return
        dataJob?.cancel()
        this.selectedFilter = selectedFilter
        this.selectedSort = sorting
        if (updateInUi) updateFiltersInUi(selectedFilter)
        dataJob = viewModelScope.launch {
            shoppingListRepository.searchShoppingList(searchQuery.orEmpty(), selectedFilter, sorting)?.collect { shoppingList ->
                _fullShoppingList.emit(shoppingList)
                _showEmptyLayout.emit(shoppingList.isEmpty())
            }
        }
    }

    private fun updateFiltersInUi(selectedFilter: FilterAction) {
        viewModelScope.launch {
            val updatedFilter = mutableListOf<Filter>()
            filter.value.forEach {
                updatedFilter.add(
                    Filter(
                        isSelected = it.filterType == selectedFilter,
                        filterType = it.filterType
                    )
                )
            }
            _filters.emit(updatedFilter.toList())
        }
    }

    fun applySort(sorting: Sorting) {
        if (this.selectedSort == sorting) return
        dataJob?.cancel()
        this.selectedSort = sorting
        updateSortInUi(sorting)
        dataJob = viewModelScope.launch {
            shoppingListRepository.searchShoppingList(searchQuery.orEmpty(), selectedFilter, sorting)?.collect { shoppingList ->
                _fullShoppingList.emit(shoppingList)
                _showEmptyLayout.emit(shoppingList.isEmpty())
            }
        }
    }

    private fun updateSortInUi(selectedSorting: Sorting) {
        viewModelScope.launch {
            val updatedSorting = mutableListOf<Sort>()
            sortings.value.forEach {
                updatedSorting.add(
                    Sort(
                        isSelected = it.type == selectedSorting,
                        type = it.type
                    )
                )
            }
            _sortings.emit(updatedSorting.toList())
        }
    }

    fun search(searchQuery: String) {
        this.searchQuery = searchQuery
        dataJob?.cancel()
        dataJob = viewModelScope.launch {
            shoppingListRepository.searchShoppingList(searchQuery, selectedFilter, selectedSort)?.collect { shoppingList ->
                _fullShoppingList.emit(shoppingList)
                _showEmptyLayout.emit(shoppingList.isEmpty())
            }
        }
    }

    fun clearSearch() {
        search("")
    }

}