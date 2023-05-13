@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.shopper.composables

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.shopper.ui.theme.LocalShopperColorsPalette
import com.example.shopper.ui.theme.White
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
    onSearch: (String) -> Unit,
) {
    var searchText by remember {
        mutableStateOf(TextFieldValue(""))
    }

    val scope = rememberCoroutineScope()
    var searchJob: Job? by remember {
        mutableStateOf(null)
    }

   Row(
       modifier = modifier
           .padding(6.dp)
           .background(color = LocalShopperColorsPalette.current.textFieldBg, shape = RoundedCornerShape(8.dp)),
       verticalAlignment = Alignment.CenterVertically
   ) {
       Spacer(modifier = Modifier.padding(start = 10.dp))
       BackButton(tint = Color.Gray) {
           onBackPress()
       }

       ShopperTextField(
           value = searchText, placeholder = "Search...", onValueChange = {
               searchText = it
               searchJob?.cancel()
               searchJob = scope.launch {
                   delay(searchDelayTime)
                   onSearch(it.text)
               }
           })

       Spacer(modifier = Modifier.padding(end = 10.dp))
   }
}

private val searchDelayTime = 500.milliseconds