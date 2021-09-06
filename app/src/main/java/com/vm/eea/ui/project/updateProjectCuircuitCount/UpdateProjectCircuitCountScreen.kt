package com.vm.eea.ui.project.updateProjectCuircuitCount

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.GridSelector

@Composable
fun UpdateProjectCircuitCountScreen(viewModel: UpdateProjectCircuitCountViewModel){
    val state by viewModel.container.stateFlow.collectAsState()

    FullPageDialog(pageTitle = "Circuits in the same conduit") {
        GridSelector(items = state.defaults, onSelect = {viewModel.onDefaultItemSelect(it)})
    }

}
