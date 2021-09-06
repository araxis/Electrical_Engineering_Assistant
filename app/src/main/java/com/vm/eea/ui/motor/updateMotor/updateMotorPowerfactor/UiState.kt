package com.vm.eea.ui.motor.updateMotor.updateMotorPowerfactor

import com.vm.eea.ui.Field
import com.vm.eea.ui.PowerfactorField

data class UiState(val powerfactor:PowerfactorField= Field.empty(),val canSubmit:Boolean=true)