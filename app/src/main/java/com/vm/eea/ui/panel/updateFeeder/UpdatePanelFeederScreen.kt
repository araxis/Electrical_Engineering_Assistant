package com.vm.eea.ui.panel.updateFeeder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.GridSelector

@Composable
fun UpdatePanelFeederScreen(viewModelPanel: UpdatePanelFeederViewModel) {
    val state by viewModelPanel.container.stateFlow.collectAsState()
//    Page1(pageTitle = "Select Feeder") {
//        Column(modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally) {
//            FeederItemSelector(state.feeders,Modifier){viewModelPanel.onSelect(it)}
//        }
//    }

    FullPageDialog(pageTitle = "Panel Feeder") {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            GridSelector(state.feeders,render = { Text(text = it.code) }){viewModelPanel.onSelect(it)}
        }
    }

}