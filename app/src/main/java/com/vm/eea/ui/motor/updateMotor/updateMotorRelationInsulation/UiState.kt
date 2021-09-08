package com.vm.eea.ui.motor.updateMotor.updateMotorRelationInsulation

import com.vm.eea.domain.Insulation
import com.vm.eea.ui.SelectableItem

data class UiState(val items:List<SelectableItem<Insulation>> = emptyList())