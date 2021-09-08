package com.vm.eea.ui.project.updateProjectCode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.StringInput

@Composable
fun UpdateProjectCodeScreen(viewModel: UpdateProjectCodeViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    FullPageDialog(pageTitle = "Project",canSubmit = state.canSubmit,onSubmit = {viewModel.onSubmit()}) {
        Column(modifier = Modifier
            .padding(16.dp),
            ) {

            StringInput(
                label = "Code",
                value = state.code,
                error = state.codeError,
                onChange = {
                viewModel.onCodeChange(it)})
            StringInput(label = "Description",
                value = state.description,
                error = null,
                onChange = { viewModel.onDescriptionChange(it) })


        }
    }
}

