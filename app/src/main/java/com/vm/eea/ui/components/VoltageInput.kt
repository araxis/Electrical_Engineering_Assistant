package com.vm.eea.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vm.eea.domain.UnitOfVoltage
import com.vm.eea.utilities.IText


data class VoltageFiledValue(val label:String, val value: String, val unitOfVoltage: UnitOfVoltage, val placeholder:String?, val error: IText?)

@Composable
fun VoltageInput(value: VoltageFiledValue,
                  modifier: Modifier = Modifier,
                  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                  keyboardActions: KeyboardActions = KeyboardActions.Default,
                  focusRequester: FocusRequester = FocusRequester.Default,
                  onValueChange:(VoltageFiledValue)->Unit){
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value.value,
            label= { Text(text = value.label) },
            keyboardOptions=keyboardOptions.copy(keyboardType = KeyboardType.Number),
            keyboardActions=keyboardActions,
            maxLines=1,
            singleLine = true,
            placeholder = {value.placeholder?.let { Text(text = it) }},
            isError = value.error!=null,
            trailingIcon = { 
                IconButton(onClick = { /*TODO*/ }) {
                    Text(text = value.unitOfVoltage.name)
                }
                
                           },
            onValueChange = {
                onValueChange(value.copy(value=it))
            },modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp))
        value.error?.let {
            Text(text = it.text(LocalContext.current),
                color= MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        }
    }

}



@Preview(showBackground = true,name = "input")
@Composable
fun Default(){
    var input by remember{ mutableStateOf(VoltageFiledValue("1-Phase Voltage","220",
        UnitOfVoltage.KV,null,null))}
     VoltageInput(value = input){
        input=it
     }
}