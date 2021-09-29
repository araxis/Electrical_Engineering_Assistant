package com.vm.eea.ui.motor.motorDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.vm.eea.ui.components.Page1
import com.vm.eea.ui.components.PropertyList
import com.vm.eea.ui.components.TileView


@ExperimentalPagerApi
@Composable
fun MotorDetailsScreen(viewModel: MotorDetailsViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Motor Settings") {
        Column {
            val pagerState = rememberPagerState(pageCount =state.calculatedInfo.size)
            HorizontalPager(state = pagerState) { page ->
                val item=state.calculatedInfo[page]
                TileView(symbol = item.first,value = item.second)
        }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
            )
            PropertyList(items = state.items, onItemClick = {viewModel.onItemSelect(it)})

    }
}
}


