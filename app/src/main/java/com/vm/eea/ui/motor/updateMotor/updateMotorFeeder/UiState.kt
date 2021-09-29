package com.vm.eea.ui.motor.updateMotor.updateMotorFeeder

import com.vm.eea.application.SelectableItem
import com.vm.eea.application.motor.MotorFeeder

data class UiState(val feeder:List<SelectableItem<MotorFeeder>> = emptyList()) {
}