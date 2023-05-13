package com.example.shopper.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopper.R

@Composable
fun NoDataLayout(
    modifier: Modifier = Modifier
) {
    Box(
        Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                modifier = Modifier.size(72.dp),
                painter = painterResource(id = R.drawable.no_data),
                contentDescription = stringResource(R.string.no_data_found)
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Text(text = stringResource(R.string.no_shopping_item_found), fontSize = 18.sp)
        }
    }
}