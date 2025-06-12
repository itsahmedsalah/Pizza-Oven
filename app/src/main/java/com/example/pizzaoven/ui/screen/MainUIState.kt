package com.example.pizzaoven.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class HomeUiState(
    val breads: List<BreadUiState> = emptyList(),
    val toppings: List<ToppingUiState> = emptyList()
)

data class BreadUiState(
    @DrawableRes val bread: Int = 0,
    val price: Double =17.0,
    val size: PizzaSize = PizzaSize.MEDIUM,
    val ingredients: List<Int> = emptyList(),
    val isFavorite: Boolean = false,
)

data class ToppingUiState(
    @DrawableRes val item: Int = 0,
    @DrawableRes val items: Int = 0,
    val isSelected: Boolean = false,
    val type: ToppingType = ToppingType.BROCCOLI
)

enum class PizzaSize(val symbol: String, val size: Dp, val bias: Float) {
    SMALL("S", 200.dp, -1F),
    MEDIUM("M", 210.dp, 0F),
    LARGE("L", 220.dp, 1F)
}

enum class ToppingType {
    BASIL,
    BROCCOLI,
    MUSHROOM,
    ONION,
    SAUSAGE
}