package com.vm.eea.ui.motor.updateMotor.updateMotorRelationConductor

import com.vm.eea.domain.Conductor
import com.vm.eea.domain.Insulation
import com.vm.eea.ui.SelectableItem

data class UiState(val items:List<SelectableItem<Conductor>> = emptyList())