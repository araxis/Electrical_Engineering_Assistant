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
import com.vm.eea.utilities.IText
import org.koin.androidx.compose.getViewModel

@Composable
fun AddProjectScreen(viewModel: AddProjectViewModel= getViewModel()) {
    val state by viewModel.container.stateFlow.collectAsState()

    FullPageDialog(pageTitle = "New Project",canSubmit = state.canSubmit,
        onSubmit = {viewModel.onSubmit()}) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp),
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
                   modifier: Modifier = Modifier,
                   codeError: IText? = null,
                   onCodeChange: (String) -> Unit,
                   onDescriptionChange:(String)->Unit,
){
    Card(modifier = modifier) {
        Column(Modifier.wrapContentHeight()) {

            OutlinedTextField(value = code,
                label= { Text(text = "Code") },
                maxLines=1,
                singleLine = true,
                isError = codeError!=null,
                placeholder={ Text(text = "enter project code") },
                onValueChange = {
                    onCodeChange(it)
                },modifier = Modifier

                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp))
            codeError?.let {
                Text(text = it.text(LocalContext.current),
                    color= MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            }
            OutlinedTextField(value = description,
                label={ Text(text = "Description") },
                placeholder={ Text(text = "enter project description") },
                onValueChange ={
                    onDescriptionChange(it)
                },modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp,bottom = 16.dp))



        }
    }
}


