package com.vm.eea.ui.calculators.motor.startMode

import com.vm.eea.application.StartMode
import com.vm.eea.ui.PowerField
import com.vm.eea.ui.calculators.FormState

data class UiState(val power: PowerField = PowerField.empty(),
                   val state: FormState<StartMode> =FormState.Calculating("waiting for input."))
