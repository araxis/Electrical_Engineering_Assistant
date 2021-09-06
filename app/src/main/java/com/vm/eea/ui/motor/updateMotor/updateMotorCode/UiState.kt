package com.vm.eea.ui.motor.updateMotor.updateMotorCode

import com.vm.eea.ui.Field
import com.vm.eea.ui.StringField

data class UiState(val code:StringField= Field.empty(),val description:StringField= Field.empty(),val canSubmit:Boolean=false)