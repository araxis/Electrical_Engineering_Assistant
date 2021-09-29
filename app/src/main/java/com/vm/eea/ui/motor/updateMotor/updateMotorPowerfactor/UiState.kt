package com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor

import com.vm.eea.ui.StringField

data class UiState(val demandFactor:StringField= StringField.empty(), val canSubmit:Boolean=true)