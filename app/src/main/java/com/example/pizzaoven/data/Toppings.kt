package com.example.pizzaoven.data

import com.example.pizzaoven.R
import com.example.pizzaoven.ui.screen.ToppingType
import com.example.pizzaoven.ui.screen.ToppingUiState

val Basil = ToppingUiState(
    item = R.drawable.basil,
    items = R.drawable.basils,
    type = ToppingType.BASIL
)
val Broccoli = ToppingUiState(
    item = R.drawable.broccoli,
    items = R.drawable.broccolis,
    type = ToppingType.BROCCOLI
)
val Mushroom = ToppingUiState(
    item = R.drawable.mushroom,
    items = R.drawable.mushrooms,
    type = ToppingType.MUSHROOM
)
val Onion = ToppingUiState(
    item = R.drawable.onion,
    items = R.drawable.onions,
    type = ToppingType.ONION
)
val Sausage = ToppingUiState(
    item = R.drawable.sausage,
    items = R.drawable.sausages,
    type = ToppingType.SAUSAGE
)

val toppings = listOf(Basil, Broccoli, Mushroom, Onion, Sausage)