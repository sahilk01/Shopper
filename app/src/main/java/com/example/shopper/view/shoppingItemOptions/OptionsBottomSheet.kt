package com.example.shopper.view.shoppingItemOptions

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopper.R
import com.example.shopper.composables.BottomSheetTopPill
import com.example.shopper.composables.ShopperButton
import com.example.shopper.composables.ShopperOutlinedButton
import com.example.shopper.model.db.entity.ShoppingItem

@Composable
fun OptionsBottomSheet(
    modifier: Modifier = Modifier,
    selectedShoppingItem: ShoppingItem? = null,
    onEditClick: (ShoppingItem) -> Unit = {},
    onDeleteClick: (ShoppingItem) -> Unit = {},
) {
    selectedShoppingItem?.let { selectedItem ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            BottomSheetTopPill()

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = selectedItem.name,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ShopperOutlinedButton(
                    modifier = Modifier.fillMaxWidth(.5f),
                    icon = painterResource(id = R.drawable.edit),
                    text = "Edit",
                    onClick = {
                        onEditClick(selectedItem)
                    }
                )

                Spacer(modifier = Modifier.padding(start = 12.dp))

                ShopperButton(
                    modifier = Modifier.fillMaxWidth(),
                    icon = painterResource(id = R.drawable.delete_outlined),
                    text = "Delete",
                    onClick = {
                        onDeleteClick(selectedItem)

                    }
                )
            }
        }
    }
}