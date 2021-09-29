package com.vm.eea.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.StringField
import com.vm.eea.ui.StringListener
import com.vm.eea.utilities.IText

@Composable
fun StringInput(label:String,
                value:String,
                error: IText?,
                modifier: Modifier = Modifier,
                suffix:String?=null,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:StringListener){

    Column(modifier = modifier) {

        OutlinedTextField(value = value,
            label = { Text(text = label) },
            maxLines = 1,
            singleLine = true,
            trailingIcon = {if(suffix?.isNotBlank()==true){
                Text(text =suffix)
            }  },
            isError = error != null,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            onValueChange = onChange, modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
        )
        error?.let {
            Text(text = it.text(LocalContext.current),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        }
    }

}

@Composable
fun StringInput(label:String,field:StringField,
                modifier: Modifier = Modifier,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:StringListener){

    StringInput(
        label = label,
        value = field.value,
        suffix = field.second,
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = onChange
    )

}