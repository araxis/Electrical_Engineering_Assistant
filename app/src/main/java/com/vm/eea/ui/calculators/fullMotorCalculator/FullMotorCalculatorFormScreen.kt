package com.vm.eea.ui.calculators.fullMotorCalculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vm.eea.R
import com.vm.eea.ui.components.*
import com.vm.eea.utilities.CosPhi


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FullMotorCalculatorFormScreen(formViewModel: FullMotorCalculatorFormViewModel) {
    val state by formViewModel.container.stateFlow.collectAsState()
    val scrollState= rememberScrollState()
        Page1(pageTitle = "Motor calculator",actions = {
            IconButton(onClick = { formViewModel.showResult() },enabled = state.canCalculate) {
                Icon(painter = painterResource(id = R.drawable.calc), null)
            }
        }) {
            Column {



                Column(modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(8.dp)
                    .fillMaxSize()) {
                    WorkingVoltageInput(field = state.voltage,
                        onValueChange = { s, u -> formViewModel.onVoltageChange(s, u) })

                    PowerInput(label = "Power",
                        field = state.power,
                        onValueChange = { s, u -> formViewModel.onPowerChange(s, u) })

                    TwoColumnRow(
                        {
                            CosPhiInput(label = CosPhi,
                                field = state.cosPhi,
                                onChange = { formViewModel.onCosPhiChanged(it) })
                        },
                        {
                            CosPhiInput(label = "Required $CosPhi",
                                field = state.demandFactor,
                                onChange = { formViewModel.onDemandFactorChanged(it) })
                        })

                    TwoColumnRow(
                        {
                            EfficiencyInput(label = "Efficiency",
                                field = state.efficiency,
                                onChange = { formViewModel.onEfficiencyChange(it) })
                        },
                        {
                            SpeedInput(label = "Revolution",
                                field = state.ratedSpeed,
                                onChange = { formViewModel.onRatedSpeedChanged(it) })
                        })
                    TwoColumnRow(
                        {
                            StartModeInput(field = state.startMode,
                                onChange = { formViewModel.onStartModeSelect(it) })
                        },
                        {
                            BooleanInput(field = state.isBidirectional,
                                onChange = { formViewModel.onIsBiDirectChange(it) })
                        })

                    ProtectionTypeInput(field = state.protectionDevice,
                        onChange = { formViewModel.onProtectionTypeSelect(it) })

                    BooleanInput(field = state.hasOverLoadProtection,
                        onChange = { formViewModel.onOverRelayChange(it) })

                }

            }
        }
}




