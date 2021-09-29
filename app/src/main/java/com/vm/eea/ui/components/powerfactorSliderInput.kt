package com.vm.eea.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.application.CosPhi
import kotlin.math.roundToInt

@Composable
fun PowerfactorSliderInput(title:String,value:Double,step:Double=.01,onChange:(Double)->Unit){

        Card(modifier = Modifier.padding(top=8.dp), border = BorderStroke(1.dp,  MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled))) {
           Column {
               Text(text = title,modifier = Modifier.padding(start = 8.dp,top = 8.dp,bottom = 0.dp))
               Row(verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.SpaceEvenly) {
                   IconButton(onClick = {
                       if(value-step>=0.1){
                           onChange(value-step)
                       }


                                        },modifier = Modifier.wrapContentSize()) {
                       Text(text = "-")
                   }
                   Slider(value = value.toFloat(),modifier=Modifier.weight(1f),
                       valueRange = .1f..1.0f,
                       onValueChange ={
                          val v= (it.toDouble() * 100.0).roundToInt() / 100.0
                           onChange(v)
                       } )
                   IconButton(onClick = {
                       if(value+step<=1.0){
                           onChange(value+step)
                       }
                   },modifier = Modifier.wrapContentSize()) {
                       Text(text = "+")
                   }
               }




           }

        }
}

@Composable
fun PowerfactorSliderInput(title:String,value:CosPhi,onChange:(CosPhi)->Unit){

    PowerfactorSliderInput(title =title,value=value.value ){
        onChange(value.copy(value = it))
    }
}
