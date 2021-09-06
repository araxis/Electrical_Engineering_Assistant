package com.vm.eea.ui.project.updateProjectCode

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.Page1
import com.vm.eea.ui.components.UpdateCodeAndDescriptionForm

@Composable
fun UpdateProjectCodeScreen(viewModel: UpdateProjectCodeViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Project") {
        Column(modifier = Modifier
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            UpdateCodeAndDescriptionForm(code = state.code,
                description = state.description,
                canExecute=state.canSubmit,
                onCodeChange = {viewModel.onCodeChange(it)},
                onDescriptionChange = {viewModel.onDescriptionChange(it)}) {
                  viewModel.onSubmit()
            }
        }
    }
}

