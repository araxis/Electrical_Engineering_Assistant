package com.vm.eea.ui.project.updateProjectConductor

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.GridSelector

@Composable
fun UpdateProjectConductorScreen(viewModel: UpdateProjectConductorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Conductor") {
        GridSelector(modifier = Modifier.padding(16.dp),items = state.defaults, onSelect = {viewModel.onItemSelect(it)})
    }
}

