package com.vm.eea.ui.panel.updatePanelCode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.UpdateCodeAndDescriptionForm2

@Composable
fun UpdatePanelCodeScreen(viewModel: UpdatePanelCodeViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Panel",canSubmit = state.canSubmit,onSubmit = {viewModel.onSubmit()}) {
        Column(modifier = Modifier
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            UpdateCodeAndDescriptionForm2(code = state.code,
                description = state.description,
                onCodeChange = {viewModel.onCodeChange(it)},
                onDescriptionChange = {viewModel.onDescriptionChange(it)})
        }
    }
}

