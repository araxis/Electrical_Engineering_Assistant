package com.vm.eea.ui.panel.updatePanelCode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.StringInput

@Composable
fun UpdatePanelCodeScreen(viewModel: UpdatePanelCodeViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Panel",canSubmit = state.canSubmit,onSubmit = {viewModel.onSubmit()}) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            StringInput(label = "Code",
                value = state.code,
                error = state.codeError,
                onChange = { viewModel.onCodeChange(it) })

            StringInput(label = "Description",
                value = state.description,
                error = null,
                onChange = { viewModel.onDescriptionChange(it) })

        }
    }
}

