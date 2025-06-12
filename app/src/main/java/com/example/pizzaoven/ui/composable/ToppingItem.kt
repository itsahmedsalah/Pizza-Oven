package com.example.pizzaoven.ui.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pizzaoven.ui.screen.ToppingUiState
import com.example.pizzaoven.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToppingItem(
    modifier: Modifier = Modifier,
    @DrawableRes itemImage: Int,
    state: ToppingUiState,
    onClickSelectedTopping: (ToppingUiState) -> Unit,
) {
    Card(
        modifier = modifier.size(54.dp),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(0.dp),
        colors = if (state.isSelected) CardDefaults.cardColors(Secondary) else CardDefaults.cardColors(
            Transparent
        ),
        onClick = { onClickSelectedTopping(state) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Center),
                painter = painterResource(itemImage),
                contentDescription = "Topping Item",
                contentScale = ContentScale.Fit,
            )
        }
    }
}