package com.vm.eea.ui.panel.panelDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vm.eea.ui.components.Page1
import com.vm.eea.ui.components.PropertyList

@Composable
fun PanelDetailsScreen(viewModel: PanelDetailsViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()

    Page1(pageTitle = "Panel Settings") {
        PropertyList(state.items){viewModel.onItemSelect(it)}
    }

}

