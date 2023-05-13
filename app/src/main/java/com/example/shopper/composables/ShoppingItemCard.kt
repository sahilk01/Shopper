@file:OptIn(ExperimentalMaterialApi::class)

package com.example.shopper.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopper.R
import com.example.shopper.composables.util.noInteractionClickable
import com.example.shopper.model.db.entity.ShoppingItem
import com.example.shopper.util.logD

@Preview
@Composable
fun ShoppingItemCard(
    modifier: Modifier = Modifier,
    shoppingItem: ShoppingItem = ShoppingItem.dummyItem,
    onShoppingItemClick: (ShoppingItem) -> Unit = {},
    onOptionsClick: (ShoppingItem) -> Unit = {},
    onBoughtClick: (ShoppingItem) -> Unit = {},
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
            .noInteractionClickable {
                onShoppingItemClick(shoppingItem)
            },
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .background(Color.DarkGray, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                text = "${shoppingItem.quantity}",
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp
            )

//            Column(
//                verticalArrangement = Arrangement.Center
//            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(end = 8.dp),
                            text = shoppingItem.name,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        logD("This is the description => ${shoppingItem.description}")
                        shoppingItem.description?.let { desc ->
                            logD("Inside description let => ${shoppingItem.description}")

                            Text(
                                text = desc,
                                fontSize = 10.sp,
                                color = Color.Gray
                            )
                        }

                    }

                    logD("checkbox recreating ${shoppingItem.isBought}")
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = shoppingItem.isBought, onCheckedChange = { checked ->
                            onBoughtClick(shoppingItem)
                        })


                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .clickable {
                                    onOptionsClick(shoppingItem)
                                }
                                .padding(4.dp),
                            painter = painterResource(id = R.drawable.vertical_menu),
                            contentDescription = "Shopping List Item Options",
                        )
                    }
                }



            }
        }
    }
//}