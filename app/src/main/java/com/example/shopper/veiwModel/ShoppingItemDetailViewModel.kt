package com.example.shopper.veiwModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.repository.shoppingDetail.ShoppingItemDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingItemDetailViewModel @Inject constructor(
    private val detailRepository: ShoppingItemDetailRepository
): ViewModel() {

    fun saveShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            detailRepository.saveShoppingItem(shoppingItem)
        }
    }

    fun updateShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            detailRepository.updateShoppingItem(shoppingItem)
        }
    }
}