@file:OptIn(ExperimentalAnimationApi::class)

package com.example.shopper.composables

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedCounter(
    modifier: Modifier = Modifier,
    count: Int,
) {

    var oldCount by remember {
        mutableStateOf(count)
    }
    SideEffect {
        oldCount = count
    }
    Row(modifier = modifier) {
        val countString = count.toString()
        val oldCountString = oldCount.toString()
        for(i in countString.indices) {
            val oldChar = oldCountString.getOrNull(i)
            val newChar = countString[i]
            val char = if(oldChar == newChar) {
                oldCountString[i]
            } else {
                countString[i]
            }
            if (oldChar != null) {
                AnimatedContent(
                    targetState = char,
                    transitionSpec = {
                        if (oldChar > newChar) {
                            slideInVertically { -it } with slideOutVertically { it }
                        } else {
                            slideInVertically { it } with slideOutVertically { -it }
                        }
                    }
                ) { char ->
                    Text(
                        text = char.toString(),
                        fontSize = 18.sp,
                        softWrap = false
                    )
                }
            }
        }
    }
}

