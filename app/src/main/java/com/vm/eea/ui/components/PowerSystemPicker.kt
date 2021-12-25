package com.vm.eea.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.application.PowerSystem


@Composable
fun PowerSystemPicker(value:PowerSystem,modifier: Modifier =Modifier,onClicked:(PowerSystem)->Unit) {
    val onClickedFunc={
        val newValue=  when(value){
            PowerSystem.SinglePhase -> PowerSystem.TwoPhase
            PowerSystem.TwoPhase -> PowerSystem.ThreePhase
            PowerSystem.ThreePhase -> PowerSystem.SinglePhase
        }
        onClicked(newValue)
    }
    Box(modifier = modifier) {
        Card(modifier = Modifier
            .padding(top = 8.dp)
            .clickable { onClickedFunc() },
            border = BorderStroke(1.dp,  MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled))){

            Text(text = value.toString(),modifier = Modifier.padding(16.dp))

        }
    }



}