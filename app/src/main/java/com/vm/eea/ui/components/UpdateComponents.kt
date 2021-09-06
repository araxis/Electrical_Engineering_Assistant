package com.vm.eea.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vm.eea.utilities.IText

@Composable
fun UpdateCodeAndDescriptionForm(code: String,
                                 description: String,
                                 modifier: Modifier = Modifier,
                                 canExecute:Boolean=false,
                                 codeError: IText? = null,
                                 onCodeChange: (String) -> Unit,
                                 onDescriptionChange:(String)->Unit,
                                 onSubmit:()->Unit){
    // val focusManager = LocalFocusManager.current
    Card(modifier = modifier) {
        Column(Modifier.wrapContentHeight()) {

            OutlinedTextField(value = code,
                label= { Text(text = "Code") },
                maxLines=1,
                singleLine = true,
                isError = codeError!=null,
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
                onValueChange ={
                    onDescriptionChange(it)
                },modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp))


            OutlinedButton(onClick = {
                onSubmit()
                // focusManager.clearFocus()
            },enabled = canExecute,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)) {

                Text(text = "Ok")
            }
        }
    }

}

@Composable
fun UpdateCodeAndDescriptionForm2(code: String,
                                 description: String,
                                 modifier: Modifier = Modifier,
                                 codeError: IText? = null,
                                 onCodeChange: (String) -> Unit,
                                 onDescriptionChange:(String)->Unit,
                                 ){
    // val focusManager = LocalFocusManager.current
    Card(modifier = modifier) {
        Column(Modifier.wrapContentHeight()) {

            OutlinedTextField(value = code,
                label= { Text(text = "Code") },
                maxLines=1,
                singleLine = true,
                isError = codeError!=null,
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
                onValueChange ={
                    onDescriptionChange(it)
                },modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp,bottom = 8.dp))



        }
    }

}

