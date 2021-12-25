package com.vm.eea.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.listItems
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import com.vm.eea.application.*
import com.vm.eea.application.protectionDevice.ProtectionDeviceType
import com.vm.eea.application.protectionDevice.ProtectionType
import com.vm.eea.ui.*
import com.vm.eea.ui.theme.lightBlue
import com.vm.eea.utilities.IText

@Composable
fun StringInput(label:String,
                value:String,
                error: IText?,
                modifier: Modifier = Modifier,
                suffixContent:(@Composable ()->Unit)? =null,
                isEnabled:Boolean=true,
                readOnly:Boolean=false,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:StringListener){
// var innerValue by remember {
//     mutableStateOf(input)
// }
//    val scope = rememberCoroutineScope()
//    val clickWithDebounce: (String) -> Unit =
//        debounceUntilLast(scope = scope,delayMillis = 300) {
//            onChange(it)
//        }

    Column(modifier = modifier) {

        TextField(
//            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor=MaterialTheme.colors.surface,
//            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            value = value,
            label = { Text(text = label) },
            maxLines = 1,
            singleLine = true,
            trailingIcon = {if(suffixContent!=null){
                suffixContent()
            }  },

            isError = error != null,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            onValueChange = onChange,
            modifier = Modifier
                .padding(top=8.dp)
                .focusRequester(focusRequester)
                .fillMaxWidth(),
            enabled = isEnabled,
            readOnly = readOnly
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
fun StringInput(label:String,
                value:String,
                error: IText?,
                modifier: Modifier = Modifier,
                suffixString:String?=null,
                isEnabled:Boolean=true,
                readOnly:Boolean=false,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:StringListener){

    val suf:@Composable ()->Unit =if(suffixString?.isNotBlank()==true){
        {Text(text =suffixString)}
    }else{
        {}
    }

    StringInput(label = label, value = value, error =error , onChange =onChange,
    readOnly = readOnly,modifier = modifier,suffixContent = suf,focusRequester = focusRequester,keyboardActions = keyboardActions,
    keyboardOptions = keyboardOptions,isEnabled = isEnabled)

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
        suffixString = field.second,
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = onChange
    )

}


@Composable
fun CoincidenceFactorInput(field:CoincidenceFactorField,
                modifier: Modifier = Modifier,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:StringListener){

    StringInput(
        label = field.label,
        value = field.input,
        suffixString = "[0-1]",
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = onChange
    )

}

@Composable
fun CosPhiInput(label:String,field:CosPhiField,
                modifier: Modifier = Modifier,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:StringListener){

    StringInput(
        label = label,
        value = field.input,
        suffixString = "[0-1]",
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = onChange
    )

}




@Composable
fun ImpedanceInput(label:String,field:ImpedanceField,
                modifier: Modifier = Modifier,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:(String,Impedance.Unit)->Unit){

    StringInput(
        label = label,
        value = field.value,
        suffixString = field.second.toString(),
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = { onChange(it,field.second) }
    )

}

@Composable
fun FrequencyInput(label:String,field:FrequencyField,
                   modifier: Modifier = Modifier,
                   keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                   keyboardActions : KeyboardActions = KeyboardActions.Default,
                   focusRequester: FocusRequester = FocusRequester.Default,
                   onChange:(String,Frequency.Unit)->Unit){

    StringInput(
        label = label,
        value = field.input,
        suffixString = field.second.toString(),
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = { onChange(it,field.second) }
    )

}

@Composable
fun ResistanceInput(label:String,field:ResistanceField,
                   modifier: Modifier = Modifier,
                   keyboardOptions: KeyboardOptions =KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                   keyboardActions : KeyboardActions = KeyboardActions.Default,
                   focusRequester: FocusRequester = FocusRequester.Default,
                   onChange:(String,Resistance.Unit)->Unit){

    StringInput(
        label = label,
        value = field.value,
        suffixString = field.second.toString(),
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = { onChange(it,field.second) }
    )

}

@Composable
fun EfficiencyInput(label:String,field:EfficiencyField,
                modifier: Modifier = Modifier,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:StringListener){

    StringInput(
        label = label,
        value = field.input,
        suffixString = "%",
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = onChange
    )

}

@Composable
fun SpeedInput(label:String,field:SpeedField,
                    modifier: Modifier = Modifier,
                    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    keyboardActions : KeyboardActions = KeyboardActions.Default,
                    focusRequester: FocusRequester = FocusRequester.Default,
                    onChange:StringListener){

    StringInput(
        label = label,
        value = field.input,
        suffixString = "RPM",
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = onChange
    )

}

@Composable
fun SlipfactorInput(label:String,field:SlipFactorField,
                    modifier: Modifier = Modifier,
                    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    keyboardActions : KeyboardActions = KeyboardActions.Default,
                    focusRequester: FocusRequester = FocusRequester.Default,
                    onChange:StringListener){

    StringInput(
        label = label,
        value = field.input,
        suffixString = "%",
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = onChange
    )

}

@Composable
fun VoltageInput(label:String, field: VoltageField,
                 modifier: Modifier = Modifier,
                 keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                 keyboardActions: KeyboardActions = KeyboardActions.Default,
                 focusRequester: FocusRequester = FocusRequester.Default,
                 onValueChange:(String, Voltage. Unit)->Unit){
    StringInput(
        label = label,
        value = field.input,
        suffixString = field.unit.toString(),
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = { onValueChange(it,field.unit) }
    )

}
@Composable
fun CurrentInput(label:String, field: CurrentField,
                 modifier: Modifier = Modifier,
                 keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                 keyboardActions: KeyboardActions = KeyboardActions.Default,
                 focusRequester: FocusRequester = FocusRequester.Default,
                 onValueChange:(String, Current. Unit)->Unit){
    StringInput(
        label = label,
        value = field.input,
        suffixString = field.unit.toString(),
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = { onValueChange(it,field.unit) }
    )

}

@Composable
fun PowerInput(label:String, field: PowerField,
                 modifier: Modifier = Modifier,
                 keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                 keyboardActions: KeyboardActions = KeyboardActions.Default,
                 focusRequester: FocusRequester = FocusRequester.Default,
                 onValueChange:(String, Power. Unit)->Unit){
    StringInput(
        label = field.label,
        value = field.input,
        suffixString = field.unit.toString(),
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = { onValueChange(it,field.unit) }
    )

}

@Composable
fun ReactivePowerInput(label:String, field: ReactivePowerField,
               modifier: Modifier = Modifier,
               keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
               keyboardActions: KeyboardActions = KeyboardActions.Default,
               focusRequester: FocusRequester = FocusRequester.Default,
               onValueChange:(String, ReactivePower. Unit)->Unit){
    StringInput(
        label = label,
        value = field.input,
        suffixString = field.unit.toString(),
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = { onValueChange(it,field.unit) }
    )

}

@Composable
fun ApparentPowerInput(label:String, field: ApparentPowerField,
                       modifier: Modifier = Modifier,
                       keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                       keyboardActions: KeyboardActions = KeyboardActions.Default,
                       focusRequester: FocusRequester = FocusRequester.Default,
                       onValueChange:(String, ApparentPower. Unit)->Unit){
    StringInput(
        label = label,
        value = field.value,
        suffixString = field.second.toString(),
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = { onValueChange(it,field.second) }
    )

}


@Composable
fun LengthInput(label: String, field:LengthField,
                modifier: Modifier = Modifier,
                keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions : KeyboardActions = KeyboardActions.Default,
                focusRequester: FocusRequester = FocusRequester.Default,
                onChange:(String, Length.Unit)->Unit){

    StringInput(
        label = label,
        value = field.value,
        suffixString = field.second.toString(),
        error = field.error,
        modifier = modifier,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        onChange = { onChange(it,field.second) }
    )
}

@Composable
fun BooleanInput(field:BooleanField,
                 modifier: Modifier = Modifier,
                 onChange:(Boolean)->Unit){


    SelectorInput(label = field.label,
        modifier=modifier,
        value = field.value,
        dialogTitle = field.label,
        items = listOf(true,false),
        display = {if(it) field.trueText else field.falseText },
        onChange = onChange)




}



@Composable
fun PowerSystemInput(field:PowerSystemField,
                   modifier: Modifier = Modifier,
                   items:List<PowerSystem> = PowerSystem.values().toList(),
                   onChange:(PowerSystem)->Unit){
    SelectorInput(label = field.label,
        modifier=modifier,
        value = field.value,
        dialogTitle = field.label,
        items = items,
        display = {it.name},
        onChange = onChange)

}



@Composable
fun<T> SelectorInput(label: String,value:T,
                     dialogTitle:String,
                     items:List<T>,
                     display:(T)->String,
                   modifier: Modifier = Modifier,
                   onChange:(T)->Unit){
   val colors= ButtonDefaults.buttonColors(
        contentColor =LocalContentColor.current.copy(LocalContentAlpha.current) ,
        //backgroundColor = MaterialTheme.colors.surface
       backgroundColor = lightBlue)
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(dialogState = dialogState) {
        title(text = dialogTitle)
        listItems(list = items,onClick = {_,item->
            onChange(item)
        }) {_, item ->
            Text(text =display(item),modifier = Modifier.padding(8.dp))
        }
    }

    Box(modifier = modifier) {
        TextButton(onClick = {  dialogState.show() },
            modifier = Modifier.padding(top=8.dp),
            colors =colors,
         shape =RoundedCornerShape(8.dp) ) {
            Row(modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
                ,horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text =label)

                IconItem(first = {
                    Text(text =display(value), modifier = Modifier
                    )
                },
                    second = {
                        Icon(imageVector = Icons.Filled.ArrowDropDown,"")


                    })


            }
        }
    }

}


@Composable
fun StartModeInput(field:StartModeField,
                   modifier: Modifier = Modifier,
                   items:List<StartMode> =StartMode.values().toList(),
                   onChange:(StartMode)->Unit){

    SelectorInput(label = field.label,
        modifier=modifier,
        value = field.value,
        dialogTitle = field.label,
        items = items,
        display = {it.name},
        onChange = onChange)

}


@Composable
fun ProtectionTypeInput(field:ProtectionTypeField,
                        modifier: Modifier = Modifier,
                        items: List<ProtectionType> =ProtectionType.values().toList(),
                        onChange:(ProtectionType)->Unit){

    SelectorInput(label = field.label,
        modifier=modifier,
        value = field.value,
        dialogTitle = field.label,
        items = items,
        display = {it.name},
        onChange = onChange)
}

@Composable
fun KeyTypeInput(field:KeyTypeField,
                        modifier: Modifier = Modifier,
                        items: List<ProtectionDeviceType> =ProtectionDeviceType.values().toList(),
                        onChange:(ProtectionDeviceType)->Unit){

    SelectorInput(label = field.label,
        modifier=modifier,
        value = field.value,
        dialogTitle = field.label,
        items = items,
        display = {it.name},
        onChange = onChange)
}

@Composable
fun WorkingVoltageInput( field: WorkingVoltageField,
                 modifier: Modifier = Modifier,
                 keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                 keyboardActions: KeyboardActions = KeyboardActions.Default,
                 focusRequester: FocusRequester = FocusRequester.Default,
                 onValueChange:(String, PowerSystem)->Unit){
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(dialogState = dialogState) {
        val items=PowerSystem.values().map {it to "V-${it.name}" }
        title("Current type")
        listItems(list = items,onClick = {_,item->
            onValueChange(field.input,item.first)
        }) {_, item ->
            Text(text = item.second,modifier = Modifier.padding(start = 16.dp,top = 8.dp,bottom = 8.dp))
        }
    }

    StringInput(
        label = field.label,
        value = field.input,
        suffixContent = {
                Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier
                    .padding(8.dp)
                    .clickable { dialogState.show() }){
                    Text(text = "V-${field.system.name}",modifier = Modifier.padding(8.dp))
                    Icon(Icons.Default.ArrowDropDown, contentDescription ="")
                }



        },
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        focusRequester = focusRequester,
        error = field.error,
        modifier = modifier,
        onChange = { onValueChange(it,field.system) }
    )

}

@Composable
fun TwoColumnRow(col1:@Composable ()->Unit,col2:@Composable ()->Unit,modifier: Modifier=Modifier){
    Row(verticalAlignment = Alignment.CenterVertically,modifier = modifier) {
        Box(modifier = Modifier
            .weight(1f)
            .padding(end = 4.dp)){
            col1()
        }
        Box(modifier = Modifier
            .weight(1f)
            .padding(start = 4.dp)){
            col2()
        }

    }

}