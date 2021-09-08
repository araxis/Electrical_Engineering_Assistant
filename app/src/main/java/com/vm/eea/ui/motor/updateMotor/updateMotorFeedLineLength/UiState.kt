package com.vm.eea.ui.motor.updateMotor.updateMotorFeedLineLength

import com.vm.eea.domain.UnitOfLength
import com.vm.eea.ui.Field
import com.vm.eea.ui.LengthField

data class UiState(val length:LengthField= Field.empty(UnitOfLength.M),val canSubmit:Boolean=false)
