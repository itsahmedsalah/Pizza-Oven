package com.example.pizzaoven.data

import com.example.pizzaoven.R
import com.example.pizzaoven.ui.screen.BreadUiState
import com.example.pizzaoven.ui.screen.PizzaSize

val Bread1 = BreadUiState(
    bread = R.drawable.bread_1,
    price = 17.0,
    isFavorite = false,
    size = PizzaSize.MEDIUM,
    ingredients = emptyList()
)
val Bread2 = BreadUiState(
    bread = R.drawable.bread_2,

    isFavorite = false, size = PizzaSize.MEDIUM, ingredients = emptyList()
)
val Bread3 = BreadUiState(
    bread = R.drawable.bread_3,

    isFavorite = false, size = PizzaSize.MEDIUM, ingredients = emptyList()
)
val Bread4 = BreadUiState(
    bread = R.drawable.bread_4,

    isFavorite = false, size = PizzaSize.MEDIUM, ingredients = emptyList()
)
val Bread5 = BreadUiState(
    bread = R.drawable.bread_5,

    isFavorite = false, size = PizzaSize.MEDIUM, ingredients = emptyList()
)

val breads = listOf(Bread1, Bread2, Bread3, Bread4, Bread5)