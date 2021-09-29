package com.vm.eea.ui.motor.updateMotor.updateMotorRelationInsulation

import com.vm.eea.application.Insulation
import com.vm.eea.application.SelectableItem

data class UiState(val items:List<SelectableItem<Insulation>> = emptyList())