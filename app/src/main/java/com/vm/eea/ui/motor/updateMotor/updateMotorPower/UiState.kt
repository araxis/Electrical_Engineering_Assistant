package com.vm.eea.ui.motor.updateMotor.updateMotorPower


import com.vm.eea.ui.PowerField

data class UiState(val power:PowerField= PowerField.empty(), val canSubmit:Boolean=true)
