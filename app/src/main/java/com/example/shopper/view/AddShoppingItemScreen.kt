package com.example.shopper.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
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
    detailViewModel: ShoppingItemDetailViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            ShopperToolbar(
                title = stringResource(id = R.string.add),
                enableBackButton = true,
                navigator = navigator
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(16.dp)
        ) {

            var enteredName by remember {
                mutableStateOf(TextFieldValue(""))
            }

            var enteredDescription by remember {
                mutableStateOf(TextFieldValue(""))
            }

            var enteredQuantity by remember {
                mutableStateOf(1)
            }

            TextLabel(text = stringResource(R.string.name))

            Spacer(modifier = Modifier.padding(top = 16.dp))


            ShopperTextField(
                value = enteredName,
                onValueChange = { name ->
                    enteredName = name
                },
                placeholder = stringResource(id = R.string.item_name)
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))


            TextLabel(text = stringResource(R.string.quantity))

            Spacer(modifier = Modifier.padding(top = 16.dp))

            ShopperNumberPicker(
                count = enteredQuantity,
                onDecreaseClick = {
                    enteredQuantity--
                },
                onIncreaseClick = {
                    enteredQuantity++
                }
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))

            TextLabel(text = stringResource(R.string.description))

            Spacer(modifier = Modifier.padding(top = 16.dp))

            ShopperTextField(
                modifier = Modifier.height(150.dp),
                value = enteredDescription,
                onValueChange = { name ->
                    enteredDescription = name
                },
                placeholder = stringResource(id = R.string.item_description)
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))

            ShopperButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                text = stringResource(R.string.save),
                onClick = {
                    detailViewModel.saveShoppingItem(
                        ShoppingItem(
                            name = enteredName.text,
                            description = enteredDescription.text,
                            quantity = enteredQuantity
                        )
                    )
                    navigator.navigateUp()
                }
            )
        }
    }
}