package com.vm.eea.ui.motor.updateMotor.updateMotorRelationSoilResistivity

import com.vm.eea.domain.UnitOfThermalResistivity
import com.vm.eea.ui.Field
import com.vm.eea.ui.ThermalResistivityField

data class UiState(val resistivity:ThermalResistivityField= Field.empty(UnitOfThermalResistivity.MW), val canSubmit:Boolean=false) {
}