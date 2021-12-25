package com.vm.eea.ui.calculators.main.startModeCalculator

import com.vm.eea.application.StartMode
import com.vm.eea.ui.PowerField
import com.vm.eea.ui.calculators.FormState

data class UiState(
    val activePower: PowerField = PowerField.empty(),
    val state: FormState<StartMode> = FormState.Calculating(""),
)
