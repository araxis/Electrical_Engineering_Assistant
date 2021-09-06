package com.vm.eea.ui.motor.updateMotor.updateMotorPower

import com.vm.eea.domain.UnitOfPower
import com.vm.eea.ui.Field
import com.vm.eea.ui.PowerField

data class UiState(val power:PowerField= Field.empty(UnitOfPower.W), val canSubmit:Boolean=true)
