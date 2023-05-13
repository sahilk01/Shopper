@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.shopper.composables

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var searchText by remember {
        mutableStateOf(TextFieldValue(""))
    }

    val scope = rememberCoroutineScope()
    var searchJob: Job? by remember {
        mutableStateOf(null)
    }

    ShopperTextField(
        modifier = modifier.padding(6.dp),
        value = searchText, placeholder = "Search...", onValueChange = {
            searchText = it
            searchJob?.cancel()
            searchJob = scope.launch {
                delay(searchDelayTime)
                onSearch(it.text)
            }
        })
}

private val searchDelayTime = 500.milliseconds