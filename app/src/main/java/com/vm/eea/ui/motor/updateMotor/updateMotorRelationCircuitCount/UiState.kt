package com.vm.eea.ui.motor.updateMotor.updateMotorRelationCircuitCount

import com.vm.eea.application.CircuitCount
import com.vm.eea.application.SelectableItem

data class UiState(val items:List<SelectableItem<CircuitCount>> = emptyList())