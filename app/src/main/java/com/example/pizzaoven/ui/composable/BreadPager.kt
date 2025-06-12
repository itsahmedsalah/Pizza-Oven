package com.example.pizzaoven.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pizzaoven.ui.screen.HomeUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BreadPager(
    state: HomeUiState,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    pizzaSize: Dp,
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) { pageIndex ->
        val currentBread = state.breads[pageIndex]
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(state.breads[pageIndex].bread),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(pizzaSize)
            )
            state.toppings.forEach { topping ->
                AnimatedVisibility(
                    visible = topping.isSelected,
                    enter = scaleIn(initialScale = 10F) + fadeIn(),
                    exit = fadeOut()
                ) {
                    Image(
                        painter = painterResource(topping.items),
                        contentDescription = "Ingredients",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(150.dp)
                    )
                }
            }
        }
    }
}