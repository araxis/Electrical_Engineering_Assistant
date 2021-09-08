package com.vm.eea.ui.motor.updateMotor.updateMotorRelationMaxVoltDrop

import com.vm.eea.ui.Field
import com.vm.eea.ui.VoltDropField

data class UiState(val voltDrop:VoltDropField= Field.empty(),val canSubmit:Boolean=false) {
}