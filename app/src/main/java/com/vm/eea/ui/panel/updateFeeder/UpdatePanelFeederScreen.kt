package com.vm.eea.ui.panel.updateFeeder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FeederItemSelector
import com.vm.eea.ui.components.Page1

@Composable
fun UpdatePanelFeederScreen(viewModel: UpdateFeederViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Select Feeder") {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            FeederItemSelector(state.feeders,Modifier){viewModel.onSelect(it)}
        }
    }

}