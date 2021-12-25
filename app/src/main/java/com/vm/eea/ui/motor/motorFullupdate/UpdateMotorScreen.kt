package com.vm.eea.ui.motor.motorFullupdate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vm.eea.ui.components.*
import com.vm.eea.utilities.CosPhi

@Composable
fun UpdateMotorScreen(viewModel: UpdateMotorViewModel) {
    val state by viewModel.container.stateFlow.collectAsState()
    val scrollState= rememberScrollState()
    FullPageDialog(pageTitle = "New motor",canSubmit = state.canCalculate,onSubmit = {viewModel.submit()}) {

        Column(modifier = Modifier
            .padding(8.dp)
            .verticalScroll(scrollState)

            .fillMaxSize()) {

            PowerSystemInput(field = state.system, onChange = { viewModel.onSystemSelect(it) })
            PowerInput(label = "Power",
                field = state.power,
                onValueChange = { s, u -> viewModel.onPowerChange(s, u) })
            TwoColumnRow(
                {
                    CosPhiInput(label = CosPhi,
                        field = state.cosPhi,
                        onChange = { viewModel.onCosPhiChanged(it) })
                },
                {
                    CosPhiInput(label = "Required $CosPhi",
                        field = state.demandFactor,
                        onChange = { viewModel.onDemandFactorChanged(it) })
                })

            TwoColumnRow(
                {
                    EfficiencyInput(label = "Efficiency",
                        field = state.efficiency,
                        onChange = { viewModel.onEfficiencyChange(it) })
                },
                {
                    SpeedInput(label = "Revolution",
                        field = state.ratedSpeed,
                        onChange = { viewModel.onRatedSpeedChanged(it) })
                })
            TwoColumnRow(
                {
                    StartModeInput(field = state.startMode,
                        onChange = { viewModel.onStartModeSelect(it) })
                },
                {
                    BooleanInput(field = state.isBidirectional,
                        onChange = { viewModel.onIsBiDirectChange(it) })
                })

            ProtectionTypeInput(field = state.protectionDevice,
                onChange = { viewModel.onProtectionTypeSelect(it) })

            BooleanInput(field = state.hasOverLoadProtection,
                onChange = { viewModel.onOverRelayChange(it) })

        }


    }
}