package com.vm.eea.ui.project.addProject

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.FullPageDialog
import com.vm.eea.ui.components.StringInput
import com.vm.eea.utilities.IText
import org.koin.androidx.compose.getViewModel

@Composable
fun AddProjectScreen(viewModel: AddProjectViewModel= getViewModel()) {
    val state by viewModel.container.stateFlow.collectAsState()

    FullPageDialog(pageTitle = "New Project",canSubmit = state.canSubmit,
        onSubmit = {viewModel.onSubmit()}) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            AddProjectForm(state.code,
                state.description,
                codeError = state.codeError,
                onDescriptionChange = { viewModel.onDescriptionChange(it) },
                onCodeChange = { viewModel.onCodeChange(it) })
        }
    }
}

@Composable
fun AddProjectForm(code: String,
                   description: String,
                   codeError: IText? = null,
                   onCodeChange: (String) -> Unit,
                   onDescriptionChange:(String)->Unit,
){


     StringInput(label = "code", value = code, error = codeError, onChange = onCodeChange)
     StringInput(label = "Description",
         value = description,
         error = null,
         onChange = onDescriptionChange)



}


