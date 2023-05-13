package com.example.shopper.veiwModel

import androidx.compose.foundation.ScrollState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.model.repository.shoppingDetail.ShoppingItemDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingItemDetailViewModel @Inject constructor(
    private val detailRepository: ShoppingItemDetailRepository
) : ViewModel() {

    private val _enteredName = MutableStateFlow(TextFieldValue(""))
    val enteredName get() = _enteredName.asStateFlow()

    private val _enteredDescription = MutableStateFlow(TextFieldValue(""))
    val enteredDescription get() = _enteredDescription.asStateFlow()

    private val _enteredQuantity = MutableStateFlow(1)
    val enteredQuantity get() = _enteredQuantity.asStateFlow()

    val scrollState = ScrollState(initial = 0)

    var id: Int? = null

    fun setName(name: TextFieldValue) {
        viewModelScope.launch {
            _enteredName.emit(name)
        }
    }

    fun setDescription(description: TextFieldValue) {
        viewModelScope.launch {
            _enteredDescription.emit(description)
        }
    }

    fun increaseQuantity() {
        viewModelScope.launch {
            _enteredQuantity.emit(++_enteredQuantity.value)
        }
    }

    fun decreaseQuantity() {
        viewModelScope.launch {
            _enteredQuantity.emit(--_enteredQuantity.value)
        }
    }

    private fun setQuantity(quantity: Int) {
        viewModelScope.launch {
            _enteredQuantity.emit(quantity)
        }
    }


    fun setShoppingItem(shoppingItem: ShoppingItem?) {
        shoppingItem?.let { item ->
            id = item.id
            setName(TextFieldValue(item.name))
            item.description?.let { desc ->
                setDescription(TextFieldValue(desc))
            }
            setQuantity(shoppingItem.quantity)
        }
    }

    fun saveShoppingItem() {
        val shoppingItem = getShoppingItem()
        if (id == null) {
            saveItem(shoppingItem)
        } else {
            updateShoppingItem(shoppingItem)
        }
    }

    private fun saveItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            detailRepository.saveShoppingItem(shoppingItem)
        }
    }

    private fun updateShoppingItem(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            detailRepository.updateShoppingItem(shoppingItem)
        }
    }

    private fun getShoppingItem(): ShoppingItem =
        id?.let {
            ShoppingItem(
                id = it,
                name = enteredName.value.text,
                description = enteredDescription.value.text.ifEmpty { null },
                quantity = enteredQuantity.value
            )
        } ?: ShoppingItem(
            name = enteredName.value.text,
            description = enteredDescription.value.text.ifEmpty { null },
            quantity = enteredQuantity.value
        )

}