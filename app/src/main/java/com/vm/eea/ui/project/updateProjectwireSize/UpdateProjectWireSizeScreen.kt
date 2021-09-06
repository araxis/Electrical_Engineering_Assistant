package com.vm.eea.ui.project.updateProjectwireSize

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.GridSelector

@Composable
fun UpdateProjectWireSizeScreen(viewModel: UpdateProjectWireSizeViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()

    FullPageDialog(pageTitle = state.pageTitle) {
        GridSelector(items = state.defaults, onSelect = {viewModel.onDefaultItemSelect(it)})
    }
}

