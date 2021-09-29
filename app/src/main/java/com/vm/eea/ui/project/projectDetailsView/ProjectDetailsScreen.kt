package com.vm.eea.ui.project.projectDetailsView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun ProjectDetailsScreen(viewModel: ProjectDetailViewModel){
    val state by viewModel.container.stateFlow.collectAsState()

        Page1(pageTitle = "Project Settings") {
            Column(modifier = Modifier.fillMaxWidth()) {
                val pagerState = rememberPagerState(pageCount =state.calculatedInfo.size)
                HorizontalPager(state = pagerState,modifier = Modifier.fillMaxWidth()) { page ->
                    val item=state.calculatedInfo[page]
                    TileView(symbol = item.first,value = item.second)
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp),
                )
                PropertyList(items = state.navigationItems, onItemClick = {viewModel.onItemSelect(it)})
            }

        }



}



