package com.vm.eea.ui.motor.updateMotor.updateMotorRelationCircuitCount

import com.vm.eea.domain.CircuitCount
import com.vm.eea.ui.SelectableItem

data class UiState(val items:List<SelectableItem<CircuitCount>> = emptyList())