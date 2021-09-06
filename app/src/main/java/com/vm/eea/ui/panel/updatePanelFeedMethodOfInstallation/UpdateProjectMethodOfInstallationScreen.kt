package com.vm.eea.ui.panel.updatePanelFeedMethodOfInstallation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.GridSelector

@Composable
fun UpdatePanelFeedMethodOfInstallationScreen(viewModel: UpdatePanelFeedMethodOfInstallationViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Method of installation") {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            GridSelector(state.defaults,modifier= Modifier.padding(top=8.dp),){viewModel.onItemSelect(it)}
    }
    }
}

