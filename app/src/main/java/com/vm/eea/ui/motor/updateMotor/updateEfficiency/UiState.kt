package com.vm.eea.ui.motor.updateMotor.updateEfficiency

import com.vm.eea.ui.StringField

data class UiState(val efficiency:StringField= StringField.empty(), val canSubmit:Boolean=false) {
}