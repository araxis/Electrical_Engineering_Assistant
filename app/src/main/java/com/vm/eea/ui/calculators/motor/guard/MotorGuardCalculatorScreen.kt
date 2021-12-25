package com.vm.eea.ui.calculators.motor.guard

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vm.eea.application.bimetal.BimetalResult
import com.vm.eea.ui.calculators.ResultDisplay
import com.vm.eea.ui.components.*
import com.vm.eea.utilities.CosPhi
import com.vm.eea.utilities.display
import com.vm.eea.utilities.displayRange

@Composable
fun MotorGuardCalculatorScreen(viewModel: MotorGuardCalculatorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Over load relay") {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            ResultDisplay(state.state,succeedContent= { RenderGuardResult(it)})

            PowerInput(label = "input",field = state.power,onValueChange = { s, u -> viewModel.onPowerChange(s, u) })
            StartModeInput(field = state.startMode, onChange = { viewModel.onStartModeChange(it) })
            KeyTypeInput(field = state.keyType, onChange = { viewModel.onProtectionChange(it) })
            WorkingVoltageInput(field =state.voltage , onValueChange ={i,s->viewModel.onVoltageChange(i,s)} )
            CosPhiInput(label = CosPhi, field = state.cosPhi, onChange ={viewModel.onCosPhiChanged(it)} )
            EfficiencyInput(label = "Efficiency", field = state.efficiency, onChange ={viewModel.onEfficiencyChange(it)} )

        }
    }
}

@Composable
fun RenderGuardResult(result: BimetalResult){
    when(result){

        is BimetalResult.Use -> {
            Column {
                val text=result.part.display({"Bimetal : ${it.displayRange()}"})
//                Text(text = "Current : ${state.input.toFormatString(empty = "0")}",modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
                Text(text =text,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)


            }
        }
        is BimetalResult.UseLess -> {
            Column {
//                Text(text = "Current : ${state.input.toFormatString(empty = "0")}",modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
                Text(text = "Not required",modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)

            }

        }
    }
}




