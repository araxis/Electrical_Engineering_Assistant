package com.vm.eea.ui.motor.updateMotor.updateMotorRelationConductor

import com.vm.eea.application.Conductor
import com.vm.eea.application.SelectableItem

data class UiState(val items:List<SelectableItem<Conductor>> = emptyList())