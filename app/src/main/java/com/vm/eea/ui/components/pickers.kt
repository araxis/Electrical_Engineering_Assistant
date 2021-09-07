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
import com.vm.eea.domain.*
import com.vm.eea.ui.PowerListener
import com.vm.eea.ui.StringListener
import com.vm.eea.utilities.IText

@Composable
fun LengthInput(label: String, value:String, unit: UnitOfLength, error: IText?,
                modifier: Modifier = Modifier,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:(String, UnitOfLength)->Unit){

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
fun PowerInput(label: String, value:String, unit: UnitOfPower, error: IText?,
               modifier: Modifier = Modifier,
               keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
               keyboardActions : KeyboardActions = KeyboardActions.Default,
               focusRequester: FocusRequester = FocusRequester.Default,
               onChange:PowerListener){

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
fun TemperatureInput(label: String, value:String, unit: UnitOfTemperature, error: IText?,
                     modifier: Modifier = Modifier,
                     keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                     keyboardActions : KeyboardActions = KeyboardActions.Default,
                     focusRequester: FocusRequester = FocusRequester.Default,
                     onChange:(String, UnitOfTemperature)->Unit){

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
fun VoltageInput(label: String, value:String, unit: UnitOfVoltage, error: IText?,
                 modifier: Modifier = Modifier,
                 keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                 keyboardActions : KeyboardActions = KeyboardActions.Default,
                 focusRequester: FocusRequester = FocusRequester.Default,
                 onChange:(String, UnitOfVoltage)->Unit){

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
fun WireSizeInput(label: String, value:String, unit: UnitOfWireSize, error: IText?,
                  modifier: Modifier = Modifier,
                  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                  keyboardActions : KeyboardActions = KeyboardActions.Default,
                  focusRequester: FocusRequester = FocusRequester.Default,
                  onChange:(String, UnitOfWireSize)->Unit){

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
fun ThermalResistivityInput(label: String, value:String, unit: UnitOfThermalResistivity, error: IText?,
                            modifier: Modifier = Modifier,
                            keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                            keyboardActions : KeyboardActions = KeyboardActions.Default,
                            focusRequester: FocusRequester = FocusRequester.Default,
                            onChange:(String, UnitOfThermalResistivity)->Unit){

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
fun StringInput(label:String,value:String, error: IText?,
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
            trailingIcon = {if(suffix?.isNotBlank()==true){Text(text =suffix)}  },
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
fun VoltDropInput(label:String,value:String, error: IText?,
                modifier: Modifier = Modifier,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:(String)->Unit){

    Column(modifier = modifier) {

        OutlinedTextField(value = value,
            label = { Text(text = label) },
            maxLines = 1,
            singleLine = true,
            trailingIcon = { Text(text ="%") },
            isError = error != null,
            keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
            keyboardActions = keyboardActions,
            onValueChange = {
                onChange(it)

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
fun PowerFactorInput(label:String,value:String, error: IText?,
                  modifier: Modifier = Modifier,
                  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                  keyboardActions : KeyboardActions = KeyboardActions.Default,
                  focusRequester: FocusRequester = FocusRequester.Default,
                  onChange:(String)->Unit){

    Column(modifier = modifier) {

        OutlinedTextField(value = value,
            label = { Text(text = label) },
            maxLines = 1,
            singleLine = true,
            trailingIcon = { Text(text ="") },
            isError = error != null,
            keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
            keyboardActions = keyboardActions,
            onValueChange = {
                onChange(it)

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
fun EfficiencyInput(label:String,value:String, error: IText?,
                  modifier: Modifier = Modifier,
                  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                  keyboardActions : KeyboardActions = KeyboardActions.Default,
                  focusRequester: FocusRequester = FocusRequester.Default,
                  onChange:(String)->Unit){

    Column(modifier = modifier) {

        OutlinedTextField(value = value,
            label = { Text(text = label) },
            maxLines = 1,
            singleLine = true,
            trailingIcon = { Text(text ="%") },
            isError = error != null,
            keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
            keyboardActions = keyboardActions,
            onValueChange = {
                onChange(it)

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