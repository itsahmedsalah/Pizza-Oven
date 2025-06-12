package com.example.pizzaoven.ui.screen

import androidx.lifecycle.ViewModel
import com.example.pizzaoven.data.breads
import com.example.pizzaoven.data.toppings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainActivityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getBreads()
        getToppings()
    }

    private fun getBreads() {
        _uiState.update { it.copy(breads = breads) }
    }

    private fun getToppings() {
        _uiState.update { it.copy(toppings = toppings) }
    }

    fun onClickSelectedTopping(topping: ToppingUiState) {
        _uiState.update { currentState ->
            val updatedToppings = currentState.toppings.map { currentTopping ->
                if (currentTopping == topping) {
                    currentTopping.copy(isSelected = !currentTopping.isSelected)
                } else {
                    currentTopping
                }
            }
            val selectedToppings = updatedToppings
                .filter { it.isSelected }
                .map { it.items }

            val updatedBreads = currentState.breads.map { bread ->
                bread.copy(ingredients = selectedToppings)
            }

            currentState.copy(breads = updatedBreads, toppings = updatedToppings)
        }
    }
}