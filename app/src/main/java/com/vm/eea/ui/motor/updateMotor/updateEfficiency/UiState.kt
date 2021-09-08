package com.vm.eea.ui.motor.updateMotor.updateEfficiency

import com.vm.eea.ui.EfficiencyField
import com.vm.eea.ui.Field

data class UiState(val efficiency:EfficiencyField= Field.empty(), val canSubmit:Boolean=false) {
}