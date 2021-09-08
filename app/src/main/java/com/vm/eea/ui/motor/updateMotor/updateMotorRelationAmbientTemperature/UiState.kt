package com.vm.eea.ui.motor.updateMotor.updateMotorRelationAmbientTemperature

import com.vm.eea.domain.UnitOfTemperature
import com.vm.eea.ui.Field
import com.vm.eea.ui.TemperatureField
import com.vm.eea.ui.VoltDropField

data class UiState(val temperature:TemperatureField= Field.empty(UnitOfTemperature.C), val canSubmit:Boolean=false) {
}