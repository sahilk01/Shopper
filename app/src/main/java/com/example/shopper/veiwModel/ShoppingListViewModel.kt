package com.example.shopper.veiwModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.repository.shoppingList.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) : ViewModel() {

    private val _fullShoppingList = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val fullShoppingList get() = _fullShoppingList.asStateFlow()

    private var selectedShoppingItem: ShoppingItem? = null

    fun setSelectedShopping(selectedShoppingItem: ShoppingItem) {
        this.selectedShoppingItem = selectedShoppingItem
    }

    fun getSelectedShopping() = selectedShoppingItem

    init {
        getFullShoppingListItems()
    }

    private fun getFullShoppingListItems() {
        viewModelScope.launch {
            shoppingListRepository.getAllShoppingItems()?.collect { shoppingList ->
                _fullShoppingList.emit(shoppingList)
            }
        }
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            shoppingListRepository.deleteShoppingItem(shoppingItem)
        }
    }

}