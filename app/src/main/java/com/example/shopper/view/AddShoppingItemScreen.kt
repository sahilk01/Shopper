package com.example.shopper.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shopper.R
import com.example.shopper.composables.*
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.veiwModel.ShoppingItemDetailViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AddShoppingItemScreen(
    navigator: DestinationsNavigator,
    detailViewModel: ShoppingItemDetailViewModel = hiltViewModel(),
    shoppingItem: ShoppingItem? = null
) {
    detailViewModel.setShoppingItem(shoppingItem)
    val enteredQuantity = detailViewModel.enteredQuantity.collectAsState()
    val enteredName = detailViewModel.enteredName.collectAsState()
    val enteredDescription = detailViewModel.enteredDescription.collectAsState()

    Scaffold(
        topBar = {
            ShopperToolbar(
                title = stringResource(id = detailViewModel.id?.let { R.string.edit } ?: R.string.add),
                enableBackButton = true,
                navigator = navigator
            )
        }
    ) {
        Column(
            Modifier
                .verticalScroll(detailViewModel.scrollState)
                .padding(it)
                .padding(16.dp),
        ) {

            TextLabel(text = stringResource(R.string.name))

            Spacer(modifier = Modifier.padding(top = 16.dp))


            ShopperTextField(
                value = enteredName.value,
                onValueChange = { name ->
                    detailViewModel.setName(name)
                },
                placeholder = stringResource(id = R.string.item_name)
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))


            TextLabel(text = stringResource(R.string.quantity))

            Spacer(modifier = Modifier.padding(top = 16.dp))

            ShopperNumberPicker(
                count = enteredQuantity.value,
                onDecreaseClick = {
                    detailViewModel.decreaseQuantity()
                },
                onIncreaseClick = {
                    detailViewModel.increaseQuantity()
                }
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))

            TextLabel(text = stringResource(R.string.description))

            Spacer(modifier = Modifier.padding(top = 16.dp))

            ShopperTextField(
                modifier = Modifier.height(150.dp),
                value = enteredDescription.value,
                onValueChange = { description ->
                    detailViewModel.setDescription(description)
                },
                placeholder = stringResource(id = R.string.item_description)
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))

            ShopperButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                text = stringResource(R.string.save),
                isEnabled = enteredName.value.text.isNotEmpty(),
                onClick = {
                    detailViewModel.saveShoppingItem()
                    navigator.navigateUp()
                }
            )
        }
    }
}