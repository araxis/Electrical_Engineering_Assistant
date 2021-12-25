package com.vm.eea.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.Destinations
import com.vm.eea.ui.MenuItem
import com.vm.eea.ui.MotorCalculatorDestinations
import com.vm.eea.ui.components.Page1
import com.vm.eea.ui.components.TwoColumnRow
import com.vm.eea.utilities.isOdd

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Home") {
        Column(Modifier.padding(8.dp)) {
            TwoColumnRow(
                {
                    OutlinedButton(onClick = {viewModel.onMenuItemClicked(MenuItem("Full Motor") { MotorCalculatorDestinations.FullMotorForm() })},
                        modifier = Modifier.padding(top = 8.dp)
                            .aspectRatio(3f, false)
                    ) {
                        Text(text = "Full Motor")
                    }
                },
                {
                    OutlinedButton(onClick = {viewModel.onMenuItemClicked(MenuItem("Full Panel") { MotorCalculatorDestinations.FullPanelForm() })},
                        modifier = Modifier.padding(top = 8.dp)
                            .aspectRatio(3f, false)
                    ) {
                        Text(text = "Full Panel")
                    }
                } )

            OutlinedButton(onClick = {viewModel.onMenuItemClicked(MenuItem("Projects")
                           { Destinations.ProjectList() })},
                modifier = Modifier.padding(top = 8.dp)
                    .aspectRatio(4f, false)
            ) {
                Text(text = "Projects")
            }
            LazyVerticalGrid(
                cells = GridCells.Fixed(2)
            ) {
                itemsIndexed(state.calculators){index,it->
                    val padding=if(index.isOdd()){
                        PaddingValues(top = 8.dp,start = 4.dp)
                    }else{
                        PaddingValues(top = 8.dp,end = 4.dp)
                    }
                    OutlinedButton(onClick = {viewModel.onMenuItemClicked(it)},
                        modifier = Modifier
                            .aspectRatio(3f, false)
                            .padding(padding)) {
                        Text(text = it.title)
                    }

                }

            }
        }
    }

}




