package com.vm.eea.ui.motor.updateMotor.updateMotorFeeder

import com.vm.eea.ui.SelectableItem
import com.vm.eea.ui.models.SimplePanel

data class UiState(val feeder:List<SelectableItem<SimplePanel>> = emptyList()) {
}