package com.example.pizzaoven.ui.screen

import android.os.Bundle
import androidx.compose.runtime.State
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzaoven.ui.composable.Header
import com.example.pizzaoven.di.AppModule
import com.example.pizzaoven.ui.theme.PizzaOvenTheme
import com.example.pizzaoven.ui.theme.WhiteBackground
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.startKoin
import com.example.pizzaoven.R
import com.example.pizzaoven.ui.composable.BreadPager
import com.example.pizzaoven.ui.composable.ToppingItem
import com.example.pizzaoven.ui.theme.Black
import com.example.pizzaoven.ui.theme.Black60
import com.example.pizzaoven.ui.theme.ButtonBackgroundColor
import com.example.pizzaoven.ui.theme.Poppins
import com.example.pizzaoven.ui.theme.Secondary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startKoin() {
            modules(AppModule)
        }
        val viewModel: MainActivityViewModel by viewModel()

        setContent {
            PizzaOvenTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(WhiteBackground)
                ) { innerPadding ->
                    PizzaOvenApp(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(WhiteBackground)
                            .fillMaxSize(),
                        viewModel
                    )
                }
            }
        }
    }
}


@Composable
fun PizzaOvenApp(modifier: Modifier = Modifier, viewModel: MainActivityViewModel) {
    val state = viewModel.uiState.collectAsState().value
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { state.breads.size }

    PizzaOvenScreen(
        modifier,
        state = state,
        pagerState = pagerState,
        onClickSelectedTopping = viewModel::onClickSelectedTopping
    )
}


@Composable
fun PizzaOvenScreen(
    modifier: Modifier = Modifier, state: HomeUiState, pagerState: PagerState,
    onClickSelectedTopping: (ToppingUiState) -> Unit,
) {
    var horizontalBias by remember { mutableFloatStateOf(PizzaSize.MEDIUM.bias) }
    val alignment by animateHorizontalAlignmentAsState(horizontalBias)

    var pizzaSize by remember { mutableStateOf(PizzaSize.MEDIUM.size) }
    val sizeAnimation by animateDpAsState(targetValue = pizzaSize)

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.plate),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(250.dp)
            )
            BreadPager(state = state, pagerState = pagerState, pizzaSize = sizeAnimation)

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "$${state.breads[pagerState.currentPage].price.toInt()}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 28.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            color = Black
        )

        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(0.5F)
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .size(48.dp)
                    .align(alignment),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(White),
            ) {}
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                PizzaSize.entries.forEach { size ->
                    Text(
                        text = size.symbol,
                        fontSize = 18.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Medium,
                        color = Black,
                        modifier = Modifier.clickable {
                            horizontalBias = size.bias
                            pizzaSize = size.size
                        },
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "CUSTOMIZE YOUR PIZZA",
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.Start),
            fontSize = 12.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.align(Alignment.Start),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(state.toppings.size) { topping ->
                ToppingItem(
                    itemImage = state.toppings[topping].item,
                    state = state.toppings[topping],
                    onClickSelectedTopping = { onClickSelectedTopping(state.toppings[topping]) }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1F))

        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 24.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonBackgroundColor,
                contentColor = White
            ),
            contentPadding = PaddingValues(vertical = 10.dp, horizontal = 40.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.cart), contentDescription = null,
                    tint = White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Add to Cart", color = White, modifier = Modifier)
            }
        }
    }


}


@Composable
private fun animateHorizontalAlignmentAsState(
    targetBiasValue: Float,
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue)
    return remember { derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) } }
}