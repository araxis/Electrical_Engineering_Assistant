package com.vm.eea.ui.calculators.motor.circuitBreaker

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vm.eea.application.ItemResult
import com.vm.eea.application.protectionDevice.ProtectionDevice
import com.vm.eea.application.protectionDevice.ProtectionDeviceResult
import com.vm.eea.ui.calculators.ResultDisplay
import com.vm.eea.ui.components.*
import com.vm.eea.utilities.CosPhi
import com.vm.eea.utilities.display
import com.vm.eea.utilities.propertyItem

@Composable
fun MotorCircuitBreakerCalculatorScreen(viewModel: MotorCircuitBreakerCalculatorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    Page1(pageTitle = "Motor circuit breaker") {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            ResultDisplay(state.state,succeedContent= { RenderCircuitBreakerResult(it)})

            PowerInput(label = "input",field = state.power,onValueChange = { s, u -> viewModel.onPowerChange(s, u) })
            WorkingVoltageInput(field = state.voltage,onValueChange = { i, s -> viewModel.onVoltageChange(i, s) })
            ProtectionTypeInput(field = state.protectionType,
                onChange = { viewModel.onProtectionTypeChange(it) })
            CosPhiInput(label = CosPhi, field = state.cosPhi, onChange ={viewModel.onCosPhiChanged(it)} )
            EfficiencyInput(label = "Efficiency", field = state.efficiency, onChange ={viewModel.onEfficiencyChange(it)} )

        }
    }
}

@Composable
fun RenderCircuitBreakerResult(result: ProtectionDeviceResult){
    val display=  when(val key=result.protection){
        is ItemResult.Found ->  key.value.display()
        is ItemResult.NotFound ->"Not found(${result.keyType.display()})"
    }
    Text(text =display,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)

}




