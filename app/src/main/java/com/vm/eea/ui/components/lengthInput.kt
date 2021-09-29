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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vm.eea.application.Length
import com.vm.eea.ui.LengthField
import com.vm.eea.utilities.IText

@Composable
fun LengthInput(label: String, value:String, unit: Length.Unit, error: IText?,
                modifier: Modifier = Modifier,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:(String, Length.Unit)->Unit){

    Column(modifier = modifier) {

        OutlinedTextField(value = value,
            label = { Text(text = label) },
            maxLines = 1,
            singleLine = true,
            trailingIcon = { Text(text = unit()) },
            isError = error != null,
            keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
            keyboardActions = keyboardActions,
            onValueChange = {
                onChange(it,unit)

            }, modifier = Modifier
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
fun LengthInput(label: String,field:LengthField,
                modifier: Modifier = Modifier,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:(String, Length.Unit)->Unit){

    LengthInput(
        label = label,
        value = field.value,
        unit = field.second,
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = onChange
    )
}